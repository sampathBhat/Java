/**
 *
 */
package dataGenerator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

public class HdfsPut extends Thread {

	private final String filePath;

	public HdfsPut(final String fPath) {
		this.filePath = fPath;
	}

	@Override
	public void run() {
		ProcessBuilder processBuilder = null;
		try {

			final InputStream in = Thread.currentThread().getClass().getClassLoader()
					.getResourceAsStream("hadoopPut.sh");
			final File newfile = new File(File.separator + "tmp" + File.separator + "hadoopPut.sh");
			java.nio.file.Files.copy(in, newfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			newfile.setExecutable(true);
			processBuilder = new ProcessBuilder("/usr/bin/sh", newfile.getAbsolutePath(), this.filePath);
			final Process process = processBuilder.start();
			process.waitFor();
			// remove file if success
			if (0 == process.exitValue()) {
				try {
					File file = new File(this.filePath);
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println(this.filePath + " file upload to hdfs status code: " + process.exitValue());
		} catch (final IOException e) {
			System.out.println(this.filePath + " IOException occurred while putting file in to hdfs " + e);
		} catch (final InterruptedException e) {
			System.out.println(this.filePath + " InterruptedException occurred while putting file in to hdfs " + e);
		}
	}
}
