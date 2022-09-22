package cn.com.ogtwelve.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Key;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	public static int Video = 0;
	public static int Pic = 1;
	public static int Share = 2;
	public static String[] pictype = { ".gif", ".jpg", ".jpeg", ".bmp" };
	public static String[] videotype = { ".rm", ".ram", ".rmvb", ".wmv",
			".mp3", ".wav", ".avi", ".swf", ".asf", ".mov", ".ra", ".mpg",
			".mpeg", ".mpe" };
	public static String[] sharefiletype = { ".zip", ".rar", ".exe", ".doc",
			".ppt", ".bmp", ".gif", ".jpg", ".jpeg", ".rm", ".ram", ".rmvb",
			".wmv", ".mp3", ".wav", ".avi", ".swf", ".asf", ".mov", ".ra",
			".mpg", ".mpeg", ".mpe", ".ppt", ".xsl", ".swf", ".html", ".txt",
			".xls" };

	public FileUtil() {

	} 

	public static String getFilePhysicalLocation(long userid) {
		String uploadPath = "";
		uploadPath = "uploadfiles/album/";
		String[] dirs = new String[3];
		for (int i = 1; i < 4; i++) {
			long temp = (long) (userid / Math.pow(10, i));
			dirs[i - 1] = (new Long(temp % 10)).toString();
		}

		try {
			String root = null;
			try {
				URL url = FileUtil.class.getResource("/");
				File classDir = new File(url.getPath());
				File f = new File(classDir.getParent());
				File f1 = new File(f.getParent());
				root = f1.getAbsolutePath();
			} catch (Exception e) {
			}
			root = URLDecoder.decode(root, "UTF-8");
			File dir = new File(root
					+ System.getProperty("file.separator") + uploadPath
					+ System.getProperty("file.separator") + dirs[2]
					+ System.getProperty("file.separator") + dirs[1]
					+ System.getProperty("file.separator") + dirs[0]);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			String tempdir = dir.toString();
			int i = tempdir.indexOf("uploadfiles");
			tempdir = tempdir.substring(i);
			tempdir = tempdir.replace('\\', '/');
			return tempdir;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * ??????????·??
	 *
	 * @param userid
	 * @param uploadPath
	 * @return
	 */
	public static String getFilePhysicalLocation(long userid, String uploadPath) {
		String[] dirs = new String[3];
		for (int i = 1; i < 4; i++) {
			long temp = (long) (userid / Math.pow(10, i));
			dirs[i - 1] = (new Long(temp % 10)).toString();
		}

		try {
			String root = null;
			try {
				URL url = FileUtil.class.getResource("/");
				File classDir = new File(url.getPath());
				File f = new File(classDir.getParent());
				File f1 = new File(f.getParent());
				root = f1.getAbsolutePath();
			} catch (Exception e) {
			}
			root = URLDecoder.decode(root, "UTF-8");
			File dir = new File(root
					+ System.getProperty("file.separator") + uploadPath
					+ System.getProperty("file.separator") + dirs[2]
					+ System.getProperty("file.separator") + dirs[1]
					+ System.getProperty("file.separator") + dirs[0]);
			if (!dir.isDirectory()) {
				dir.mkdirs();
			}
			String tempdir = dir.toString();
			int i = tempdir.indexOf("uploadfiles");
			tempdir = tempdir.substring(i);
			tempdir = tempdir.replace('\\', '/');
			return tempdir;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @param path
	 * @throws Exception
	 */
	static public void Create_dir(String path) throws Exception {
		File dirName = new File(path);
		if (dirName.exists())
			dirName.delete();
		dirName.mkdirs();// ??b???
	}

	/**
	 * ????????????
	 * 
	 * @param path
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	static public File Create_file(String path, String filename)
			throws Exception {
		File fileName = new File(path, filename);
		if (fileName.exists())
			fileName.delete();
		fileName.createNewFile();// ??b???
		return fileName;
	}

	/**
	 * 写入字符串到文件中
	 * @param str 待写入内容
	 * @param file 目标文件
	 * @param overwrite 是否覆盖
	 * @throws Exception
	 */
	static public void Write_file(String str, File file,boolean overwrite) throws Exception {
		FileWriter fw = new FileWriter(file,!overwrite);
		fw.write(str);
		fw.close();
	}

	/**
	 * ??????????(?????????????)
	 * 
	 * @param path
	 * @throws Exception
	 */
	static public void Delete_dir(String path) throws Exception {
		File dirName = new File(path);
		if (dirName.exists())
			dirName.delete();// ?????????????????????)
	}

	/**
	 * ?????????????????????????????????????
	 * 
	 * @param vpath
	 * @return
	 */
	static public boolean Deldir(String vpath) {
		String pathName = "";
		try {
			pathName = vpath;
			File delPath = new File(pathName);
			if (delPath.exists()) {
				String[] strList;
				strList = delPath.list();
				for (int i = 0; i < strList.length; i++) {
					String fileTree = pathName + "/" + strList[i];
					File turePath = new File(delPath.getPath(), strList[i]);
					boolean isdir = turePath.isDirectory();
					if (isdir) {
						boolean reDir = Deldir(turePath.getPath());
					} else {
						boolean reBool = turePath.delete();
					}
				}
				boolean rBool = delPath.delete();
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	/**
	 * ???)??????????????
	 * 
	 * @param filename
	 * @return
	 */
	public String getFileTypeImg(String filename) {
		String str = "00.gif";
		if (filename == null || filename.trim().length() < 5) {
			str = "00.gif";
		} else {
			filename = filename.trim();
			filename = filename.substring(filename.length() - 4,
					filename.length());
			filename = filename.toLowerCase();

			if (filename.equals(".txt")) {
				str = "txt.gif";
			} else if (filename.equals(".xml")) {
				str = "xml.gif";
			} else if (filename.equals(".xsl") || filename.equals("xslt")) {
				str = "xsl.gif";
			} else if (filename.equals(".doc")) {
				str = "doc.gif";
			} else if (filename.equals(".css")) {
				str = "css.gif";
			} else if (filename.equals(".htm") || filename.equals("html")) {
				str = "htm.gif";
			} else if (filename.equals(".gif")) {
				str = "gif.gif";
			} else if (filename.equals(".jpg") || filename.equals("jpeg")) {
				str = "jpg.gif";
			} else if (filename.equals(".psd")) {
				str = "psd.gif";
			} else if (filename.equals(".mid")) {
				str = "mid.gif";
			} else if (filename.equals(".wav")) {
				str = "wav.gif";
			} else if (filename.equals(".avi")) {
				str = "avi.gif";
			} else if (filename.equals(".rar")) {
				str = "rar.gif";
			} else if (filename.equals(".zip")) {
				str = "zip.gif";
			} else {
				str = "00.gif";
			}
			filename = filename.substring(filename.length() - 3,
					filename.length());
			if (filename.equals(".js")) {
				str = "js.gif";
			}
		}
		return str;
	}

	/**
	 * ?????????????????????????
	 * 
	 * @param path
	 * @throws Exception
	 */
	static public void Delete_file(String path) throws Exception {
		File fileName = new File(path);
		if (fileName.exists())
			fileName.delete();// ??????????
	}
	

	/**
	 * ?????????????????μ?????б?
	 * 
	 * @param dirpath
	 * @return
	 * @throws Exception
	 */
	static public String[] List_files(String dirpath) throws Exception {
		File file = new File(dirpath);
		File files[] = file.listFiles();
		String[] fileslist = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			String NewsFileName = files[i].getName();
			fileslist[i] = dirpath + "\\" + NewsFileName;
		}
		return fileslist;
	}

	/**
	 * ????????????? ?????????(2002-1-24 9:52:47)
	 * 
	 * @param from
	 *            java.lang.String
	 * @param to
	 *            java.lang.String
	 */

	static public boolean copy(String from, String to) {
		try {
			to = replace(to, " \\", "/ <file://\\> ");
			String toPath = to.substring(0, to.lastIndexOf("/"));
			File f = new File(toPath);
			if (!f.exists())
				f.mkdirs();
			BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream(from));
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(to));
			int c;
			while ((c = bin.read()) != -1)
				bout.write(c);
			bin.close();
			bout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static long downloadHTTP(String srcHttpFile, String destFile,
			long fileSize) {
		srcHttpFile = srcHttpFile.replaceAll("\\\\", "/");
		destFile = destFile.replaceAll("\\\\", "/");

		// 下载网络文件
		long bytesum = 0;
		int byteread = 0;
		InputStream is = null;
		RandomAccessFile raf = null;
		HttpURLConnection httpConnection = null;
		try {
			srcHttpFile = URLEncoder.encode(srcHttpFile,"UTF-8");
			srcHttpFile = srcHttpFile.replaceAll("%3A", ":").replaceAll("%2F", "/");
			URL url = new URL(srcHttpFile);
		 
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestProperty("User-Agent", "NetFox");
			String sProperty = "bytes=" + fileSize + "-";
			httpConnection.setRequestProperty("RANGE", sProperty);
			is = httpConnection.getInputStream();
			raf = new RandomAccessFile(destFile, "rw");
			raf.seek(fileSize);
			byte[] buffer = new byte[1204 * 100];

			while ((byteread = is.read(buffer)) != -1) {
				bytesum += byteread;
				raf.write(buffer, 0, byteread);
			}

		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		} finally {
			try {
				if (raf != null) {
					raf.close();
				}
				if (is != null) {
					is.close();
				}
				if (httpConnection != null) {
					httpConnection.disconnect();
				}
			} catch (IOException e) {
				return 0;
			}
		}

		return bytesum;
	}
	
	static public long copyAndEncrypt(String from, String to) {
		FileInputStream bin = null;
		FileOutputStream bout = null;
		try {
			String fileName = getFileName(to);
			SecureRandom sr = new SecureRandom();
			Key privateKey = getKey(fileName);// 存放解密密码
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec spec = new IvParameterSpec(privateKey.getEncoded());
			cipher.init(Cipher.ENCRYPT_MODE, privateKey, spec, sr);

			File f = new File(to);
			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}

			bin = new FileInputStream(from);
			bout = new FileOutputStream(to);
			int c = 0;
			long result = 0;
			byte[] d;
			byte[] bytes = new byte[1024];
			while ((c = bin.read(bytes)) != -1) {
				result += c;
				if (c != 1040) {
					d = new byte[c];
					for (int i = 0; i < c; i++) {
						d[i] = bytes[i];
					}
					bout.write(cipher.doFinal(d));
				} else {
					bout.write(cipher.doFinal(bytes));
				}
			}
			return result;
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				if (bin != null) {
					bin.close();
				}
				if (bout != null) {
					bout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ?滻??????????? ?????????(2002-1-18 13:25:21)
	 * 
	 * @return java.lang.String
	 * @param ss
	 *            java.lang.String
	 */
	static public String replace(String srcStr, String oldStr, String newStr) {
		int i = srcStr.indexOf(oldStr);
		StringBuffer sb = new StringBuffer();
		if (i == -1)
			return srcStr;
		sb.append(srcStr.substring(0, i) + newStr);
		if (i + oldStr.length() < srcStr.length())
			sb.append(replace(
					srcStr.substring(i + oldStr.length(), srcStr.length()),
					oldStr, newStr));
		return sb.toString();
	}

	/**
	 * ??????
	 * 
	 * @param from
	 * @param to
	 * @throws Exception
	 */
	static public void MoveFile(String from, String to) throws Exception {
		boolean ok = copy(from, to);
		if (ok)
			Delete_file(from);
	}

	/**
	 * 获取下载文件的大小
	 * 
	 * @param f下载的文件
	 * @return 返回文件大小
	 * @throws Exception
	 */
	public static long getFileSize(File f) {
		long size = 0;
		FileInputStream fis = null;
		try {

			fis = new FileInputStream(f);
			size = fis.available();
			fis.close();
			return size;

		} catch (Exception e) {
			return 0;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					return size;
				}
			}
		}
	}

	/**
	 * 生成加密key
	 * 
	 * @param strKey
	 * @return
	 */
	private static SecretKey getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(strKey.getBytes());
			_generator.init(128, secureRandom);
			return _generator.generateKey();
		} catch (Exception e) {
			throw new RuntimeException("初始化密钥出现异常");
		}
	}

	/**
	 * nio拷贝
	 * 
	 * @param inFile
	 *            源文件
	 * @param outFile
	 *            目标文件
	 * @return
	 * @throws Exception
	 */
	public static long fileChannelCopy(File inFile, File outFile)
			throws Exception {
		FileInputStream fin = new FileInputStream(inFile);
		FileOutputStream fout = new FileOutputStream(outFile);
		FileChannel inc = fin.getChannel();
		FileChannel outc = fout.getChannel();
		int bufferLen = 2097152;
		ByteBuffer bb = ByteBuffer.allocateDirect(bufferLen);
		while (true) {
			int ret = inc.read(bb);
			if (ret == -1) {
				fin.close();
				fout.flush();
				fout.close();
				break;
			}
			bb.flip();
			outc.write(bb);
			bb.clear();
		}

		return inFile.length();

	}

	/**
	 * 根据文件路径返回文件名称
	 * 
	 * @param filePath文件路径
	 * @return 文件名称
	 */
	public static String getFileName(String filePath) {

		if (filePath != null && !filePath.isEmpty()) {
			if (filePath.lastIndexOf('\\') > filePath.lastIndexOf('/')) {
				filePath = filePath.substring(filePath.lastIndexOf('\\') + 1);
			} else {
				filePath = filePath.substring(filePath.lastIndexOf('/') + 1);
			}
		}
		return filePath;
	}
	
	/**
	 * 根据文件路径返回文件目录
	 * 
	 * @param filePath文件路径
	 * @return 文件名称
	 */
	public static String getFilePath(String filePath) {
		if(filePath.isEmpty()){
			return null;
		}
		 String fileName = getFileName(filePath);
		 
		 return filePath.replace(fileName, "");
	}
	
	
	/**
	 * 根据文件路径返回文件目录
	 * 
	 * @param filePath文件路径
	 * @return 文件名称
	 */
	public static String getParentFilePath(String filePath) {
		if(filePath.isEmpty()){
			return null;
		}
		 String fileName = getFileName(filePath);
		 
		 filePath=filePath.replace(fileName, "");
		 filePath=filePath.substring(0, filePath.length()-1);
		 
		 String szParentFilePath = getFileName(filePath);
		 
		 return szParentFilePath;
		 
	}
	
	
   public static List<File> iteratorFile(File rootFile, String fileRegex){
	   
	   if(!rootFile.exists()){
		   return null;
	   }
	   
	   List<File> fileList = new ArrayList<File>();
	   
	   if(rootFile.isDirectory()){
		   File[] fileArr = rootFile.listFiles();
		   for (File file : fileArr) {
			fileList.addAll(iteratorFile(file, fileRegex));
		   }
	   } else {
		   if(fileRegex == null){
			   fileList.add(rootFile);
		   }else{
			   if(rootFile.getName().matches(fileRegex)){
				   fileList.add(rootFile);
			   }  
		   }
	   }
	   
	   return fileList;
   }
   
   public static List<File> iteratorDirectory(File rootFile, String fileRegex){
	   
	   if(!rootFile.exists() || !rootFile.isDirectory()){
		   return null;
	   }
	  
	   List<File> fileList = new ArrayList<File>();
	   
	   if(rootFile.getName().matches(fileRegex)){
		   fileList.add(rootFile);
	   }else{
		   File[] fileArr = rootFile.listFiles();
		   for (File file : fileArr) {
			fileList.addAll(iteratorFile(file, fileRegex));
		   }
	   }
	   
	   return fileList;
   }

	/**
	 * 此方法逐行读取文本文件，并和传入的文本比较，有，返回true；无，返回false；
	 *
	 * @param fileDb  文件路径
	 * @param keyword 查找的文本
	 * @return
	 */
	public static boolean readFileRecode(String fileDb, String keyword) {
		boolean result = false;
		File file = new File(fileDb);

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), "GBK"); // 编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (lineTxt.equals(keyword)) {
						result = true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 此方法动态生成文本文件，并逐行写入内容
	 *
	 * @param fileDb  输出路径
	 * @param keyword 文本内容
	 */
//    public static void writeFileRecode(String fileDb, String keyword) {
//        File file = new File(fileDb);
//        FileWriter writer = null;
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            writer = new FileWriter(fileDb, true);
//            writer.write(keyword + "\r\n");
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

	public static void main(String[] args) {
		 
		try {
			Write_file("%%%%%%%%%%%%%%%%%%%aa\r\n", new File("D:\\China_XjRaster_Code1.txt"),true);
		} catch (Exception e) {
		}
	}
}
