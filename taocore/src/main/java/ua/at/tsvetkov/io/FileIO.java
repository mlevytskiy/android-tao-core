/**
 * ****************************************************************************
 * Copyright (c) 2014 Alexandr Tsvetkov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 * <p/>
 * Contributors:
 * Alexandr Tsvetkov - initial API and implementation
 * <p/>
 * Project:
 * TAO Core
 * <p/>
 * License agreement:
 * <p/>
 * 1. This code is published AS IS. Author is not responsible for any damage that can be
 * caused by any application that uses this code.
 * 2. Author does not give a garantee, that this code is error free.
 * 3. This code can be used in NON-COMMERCIAL applications AS IS without any special
 * permission from author.
 * 4. This code can be modified without any special permission from author IF AND ONLY IF
 * this license agreement will remain unchanged.
 * ****************************************************************************
 */
package ua.at.tsvetkov.io;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ua.at.tsvetkov.application.AppConfig;
import ua.at.tsvetkov.util.Log;

/**
 * Methods for file and directory operations
 *
 * @author Alexandr Tsvetkov 2014
 */
public final class FileIO {

    private static final String CACHE = "cache/";
    private static final int BUFFER_SIZE = 8192;

    private FileIO() {

    }

    /**
     * Copy file from source to destination
     *
     * @param srcFileName source file path
     * @param dstFileName destination file path
     * @return true if success
     */
    public static boolean copy(String srcFileName, String dstFileName) {
        if (srcFileName == null || srcFileName.length() == 0) {
            Log.e("Source file name is empty.");
            return false;
        }
        if (dstFileName == null) {
            Log.e("Destination file name is empty.");
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new BufferedInputStream(new FileInputStream(srcFileName));
            out = new BufferedOutputStream(new FileOutputStream(dstFileName));
            byte[] buffer = new byte[BUFFER_SIZE];
            int count;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
            out.flush();
        } catch (IOException e) {
            Log.e("Can't copy file " + srcFileName + " to " + dstFileName, e);
            return false;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e(e);
                return false;
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                Log.e(e);
                return false;
            }
        }
        Log.v("Success copied file " + srcFileName + " to " + dstFileName);
        return true;
    }

    /**
     * Copy file from source to destination
     *
     * @param srcFileName source file name
     * @param dstFile     destination file
     * @return true if success
     */
    public static boolean copy(String srcFileName, File dstFile) {
        if (srcFileName == null || srcFileName.length() == 0) {
            Log.e("Source file name is empty.");
            return false;
        }
        if (dstFile == null) {
            Log.e("Destination file is null.");
            return false;
        }
        return copy(srcFileName, dstFile.getAbsoluteFile());
    }

    /**
     * Copy file from source to destination
     *
     * @param srcFile     source file
     * @param dstFileName destination file name
     * @return true if success
     */
    public static boolean copy(File srcFile, String dstFileName) {
        if (srcFile == null || !srcFile.exists()) {
            Log.e("Source file is null or not exist.");
            return false;
        }
        if (dstFileName == null) {
            Log.e("Destination file name is empty.");
            return false;
        }
        return copy(srcFile.getAbsoluteFile(), dstFileName);
    }

    /**
     * Copy file from source to destination
     *
     * @param srcFile source file
     * @param dstFile destination file
     * @return true if success
     */
    public static boolean copy(File srcFile, File dstFile) {
        if (srcFile == null || !srcFile.exists()) {
            Log.e("Source file is null or not exist.");
            return false;
        }
        if (dstFile == null) {
            Log.e("Destination file is null.");
            return false;
        }
        return copy(srcFile.getAbsoluteFile(), dstFile.getAbsoluteFile());
    }

    /**
     * Copy file from assets source to destination
     *
     * @param context        base app context
     * @param assetsFileName assets file name
     * @param dstFileName    destination of copy
     * @return true if success
     */
    public static boolean copyAsset(Context context, String assetsFileName, String dstFileName) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(context.getAssets().open(assetsFileName));
            out = new BufferedOutputStream(new FileOutputStream(dstFileName));
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            Log.e("Can't copy file from assets " + assetsFileName + " to " + dstFileName, e);
            return false;
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                Log.e(e);
                return false;
            }
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                Log.e(e);
                return false;
            }
        }
        Log.v("Success copy file " + assetsFileName + " to " + dstFileName);
        return true;
    }

    /**
     * Copy file from assets source to destination
     *
     * @param context        base app context
     * @param assetsFileName assets file name
     * @param dstFile        destination of copy
     * @return true if success
     */
    public static boolean copyAsset(Context context, String assetsFileName, File dstFile) {
        if (assetsFileName == null || assetsFileName.length() == 0) {
            Log.e("Assets file name is empty.");
            return false;
        }
        if (dstFile == null) {
            Log.e("Destination file is null.");
            return false;
        }
        return copyAsset(context, assetsFileName, dstFile.getAbsolutePath());
    }

    /**
     * Delete file
     *
     * @param fileName file for delete
     * @return true if success
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        boolean result = file.delete();
        if (result) {
            Log.v("File success deleted " + fileName);
        } else {
            Log.w("Fail to delete file " + fileName);
        }
        return result;
    }

    /**
     * Delete content from directory
     *
     * @param pathName path for delete a content
     */
    public static void deleteDirContent(String pathName) {
        File path = new File(pathName);
        String[] files = path.list();
        if (files != null) {
            for (String fileName : files) {
                File file = new File(fileName);
                boolean result = file.delete();
                if (result) {
                    Log.v("File success deleted " + fileName);
                } else {
                    Log.w("Fail to delete file " + fileName);
                }
            }
        }
    }

    /**
     * Rename file
     *
     * @param srcFileName source file name
     * @param dstFileName reamed file name
     * @return true if success
     */
    public static boolean rename(String srcFileName, String dstFileName) {
        File src = new File(srcFileName);
        File dst = new File(dstFileName);
        boolean result = src.renameTo(dst);
        if (result) {
            Log.v("File success renamed to " + dstFileName);
        } else {
            Log.w("Fail to rename file " + srcFileName);
        }
        return result;
    }

    /**
     * Default working dir with File.separatorChar at the end of string
     *
     * @return Default working dir
     */
    public static String getDir() {
        return AppConfig.getApplicationWorkingDir();
    }

    /**
     * Return default cache dir with File.separatorChar at the end of string.
     *
     * @return default cache dir
     */
    public static String getCacheDir() {
        return createDir(CACHE);
    }

    /**
     * Create cache dir in app package default directory
     */
    public static void createCacheDir() {
        createDir(CACHE);
    }

    /**
     * Return full path to cache file from given file name. If cache dir is not present then will be create.
     *
     * @param fileName file name without a path
     * @return full path to cache file
     */
    public static String getCacheFileName(String fileName) {
        return getCacheDir() + fileName;
    }

    /**
     * Default working dir with subdir and File.separatorChar at the end of string
     *
     * @return full path with appended subdir
     */
    public static String getDir(String subdir) {
        if (subdir.charAt(subdir.length() - 1) != File.separatorChar) {
            subdir = subdir + File.separatorChar;
        }
        if (subdir.charAt(0) == File.separatorChar) {
            return AppConfig.getApplicationWorkingDir() + subdir;
        } else {
            return AppConfig.getApplicationWorkingDir() + File.separatorChar + subdir;
        }
    }

    /**
     * Return full path to file from given file name in work dir.
     *
     * @param fileName file name
     * @return full path to file
     */
    public static String getFileName(String fileName) {
        return getFileName(null, fileName);

    }

    /**
     * Return full path to file from given file name in work dir.
     *
     * @param subdir   subdir in work dir, possible to be empty or null.
     * @param fileName file name
     * @return full path to file
     */
    public static String getFileName(String subdir, String fileName) {
        String dir;
        if (subdir != null && subdir.length() > 0) {
            dir = getDir(subdir);
        } else {
            dir = getDir();
        }
        return dir + fileName;
    }

    /**
     * Return file name without extension
     *
     * @param path full path to file
     * @return file name without extension
     */
    public static String getFileNameNoExtension(String path) {
        String name = "";
        try {
            int pos = path.lastIndexOf(File.separator) + 1;
            int dotPos = path.lastIndexOf(".") + 1;
            name = path.substring(pos, dotPos);
        } catch (Exception e) {
            Log.e(e);
        }
        return name;
    }

    /**
     * Create a dirs in the working dir
     *
     * @param subdir sub directory
     * @return full path
     */
    public static String createDir(String subdir) {
        String path = AppConfig.getApplicationWorkingDir() + subdir;
        File dir = new File(path);
        if (!dir.exists()) {
            boolean result = dir.mkdirs();
            if (result) {
                Log.i("++ Created the Directory: " + path);
            } else {
                Log.w("-- Creating the Directory is failed: " + path);
            }
            return "";
        }
        return path;
    }

    /**
     * Call this method to delete any cache created by app
     */
    public static void clearCashedApplicationData(Context context) {
        File cache = context.getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                File f = new File(appDir, s);
                if (deleteDir(f)) {
                    Log.i(String.format("**************** DELETED -> (%s) *******************", f.getAbsolutePath()));
                }
            }
        }
    }

    /**
     * Delete directory with a subdirs and a files
     *
     * @param dir directory for delete
     * @return tue if success
     */
    public static boolean deleteDir(File dir) {
        if (dir == null) {
            Log.e("Directory eq null - can't delete.");
            return false;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String element : children) {
                boolean success = deleteDir(new File(dir, element));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
