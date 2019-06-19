package tp1.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

/**
 * Class to handle the listing of a folder content
 * 
 * @author irakoze and lepretre
 *
 */
public class ListDirectory {

	/**
	 * Generates a string with all the files and folder in the directory given in
	 * the parameter.
	 * 
	 * @param directory the repertory to list all the content
	 * @return string
	 * @throws Exception
	 */
	public static String generateList(String directory) throws Exception {
		String list = "";
		File folder = checkDirectory(directory);
		if (folder == null) {
			return null;
		}

		File[] files = folder.listFiles();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd HH:ss", Locale.ENGLISH);

		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			list += (file.isDirectory() ? "d" : "-");

			PosixFileAttributes attr = Files.readAttributes(Paths.get(file.getAbsolutePath()),
					PosixFileAttributes.class);

			String group = attr.group().getName();
			String owner = attr.owner().getName();
			Set<PosixFilePermission> permissions = attr.permissions();

			String permissionsString = generatePermissionsString(permissions);

			list += permissionsString + " 1 " + owner + " " + group + " " + file.length() + " "
					+ dateFormat.format(file.lastModified()) + " " + file.getName() + "\r\n";
		}
		return list;
	}

	/**
	 * Return a repertory if it exists and creates it if not
	 *
	 * @param directory the name the repertory to return
	 * @return File
	 */
	private static File checkDirectory(String directory) {
		File folder = new File(directory);
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		}
		return folder;
	}

	/**
	 * Generate the rights on the file according to the set given in the parameter.
	 *
	 * @param permissions Set with all the permission of a file or folder
	 * @return permissions String
	 */
	private static String generatePermissionsString(Set<PosixFilePermission> permissions) {
		return (permissions.contains(PosixFilePermission.OWNER_READ) ? "r" : "-")
				+ (permissions.contains(PosixFilePermission.OWNER_WRITE) ? "w" : "-")
				+ (permissions.contains(PosixFilePermission.OWNER_EXECUTE) ? "x" : "-")
				+ (permissions.contains(PosixFilePermission.GROUP_READ) ? "r" : "-")
				+ (permissions.contains(PosixFilePermission.GROUP_WRITE) ? "w" : "-")
				+ (permissions.contains(PosixFilePermission.GROUP_EXECUTE) ? "x" : "-")
				+ (permissions.contains(PosixFilePermission.OTHERS_READ) ? "r" : "-")
				+ (permissions.contains(PosixFilePermission.OTHERS_WRITE) ? "w" : "-")
				+ (permissions.contains(PosixFilePermission.OTHERS_EXECUTE) ? "x" : "-");
	}

}
