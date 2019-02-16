package tp1.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

public class ListDirectory {

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

			PosixFileAttributes attr = Files.readAttributes(Paths.get(file.getAbsolutePath()), PosixFileAttributes.class);

			String group = attr.group().getName();
			String owner = attr.owner().getName();
			Set<PosixFilePermission> permissions = attr.permissions();

			String permissionsString = generatePermissionsString(permissions);

			list += permissionsString + " 1 " + owner + " " + group + " "
					+ file.length() + " " + dateFormat.format(file.lastModified())
					+ " " + file.getName() + "\r\n";
		}
		return list;
	}

	/**
	 * Méthode permettant de créer un répertoire s'il n'existe pas ou retourner l'existant
	 *
	 * @param directory
	 * @return
	 */
	private static File checkDirectory(String directory) {
		File folder = new File(directory);
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdir();
		}
		return folder;
	}

	/**
	 * Génère la liste des droits en fonction d'un set de permissions donné
	 *
	 * @param permissions
	 * @return
	 */
	private static String generatePermissionsString(Set<PosixFilePermission> permissions) {
		return (permissions
				.contains(PosixFilePermission.OWNER_READ) ? "r" : "-")
				+ (permissions
				.contains(PosixFilePermission.OWNER_WRITE) ? "w"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.OWNER_EXECUTE) ? "x"
				: "-")
				+ (permissions.contains(PosixFilePermission.GROUP_READ) ? "r"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.GROUP_WRITE) ? "w"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.GROUP_EXECUTE) ? "x"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.OTHERS_READ) ? "r"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.OTHERS_WRITE) ? "w"
				: "-")
				+ (permissions
				.contains(PosixFilePermission.OTHERS_EXECUTE) ? "x"
				: "-");
	}

}
