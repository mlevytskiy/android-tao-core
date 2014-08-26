/*******************************************************************************
 * Copyright (c) 2010 Alexandr Tsvetkov.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * Contributors:
 *     Alexandr Tsvetkov - initial API and implementation
 *
 * Project:
 *     TAO Util
 *
 * License agreement:
 *
 * 1. This code is published AS IS. Author is not responsible for any damage that can be
 *    caused by any application that uses this code.
 * 2. Author does not give a garantee, that this code is error free.
 * 3. This code can be used in NON-COMMERCIAL applications AS IS without any special
 *    permission from author.
 * 4. This code can be modified without any special permission from author IF AND ONLY IF
 *    this license agreement will remain unchanged.
 ******************************************************************************/
package ua.at.tsvetkov.util;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Prepare formatted string an object for logging
 * 
 * @author Tsvetkov A.O. 2011. Email al@ukr.net
 */
public class LogFormatter {

   private static final char   PREFIX    = '|';
   private static final String NL        = "\n";
   private static final String HALF_LINE = "---------------------";
   private static final String LINE      = "----------------------------------------------------------" + NL;

   /**
    * Return String representation of map. Each item in new line.
    * 
    * @param map
    */
   public static String toLines(Map<?, ?> map) {
      int max = 0;
      for (Map.Entry<?, ?> item : map.entrySet()) {
         int length = item.getKey().toString().length();
         if (max < length)
            max = length;
      }
      StringBuilder sb = new StringBuilder();
      String fomatString = "%-" + max + "s = %s";
      sb.append(HALF_LINE + " Map " + HALF_LINE + NL);
      for (Map.Entry<?, ?> item : map.entrySet()) {
         sb.append(String.format(fomatString, item.getKey(), item.getValue()));
         sb.append(NL);
      }
      sb.append(LINE);
      return sb.toString();
   }

   /**
    * Return String representation of class. Each field in new line.
    * 
    * @param myObj a class for representation
    * @return
    */
   public static String toLines(Object myObj) {
      Class<?> cl = myObj.getClass();
      int max = 0;
      Field[] fields = cl.getDeclaredFields();
      for (int i = 0; i < fields.length; i++) {
         int length = fields[i].getName().length();
         if (max < length)
            max = length;
      }
      String fomatString = PREFIX + "%-" + max + "s = %s" + NL;
      StringBuilder sb = new StringBuilder();
      sb.append(HALF_LINE + cl.getSimpleName() + HALF_LINE + NL);
      for (int i = 0; i < fields.length; i++) {
         try {
            Field myField = getField(cl, fields[i].getName());
            myField.setAccessible(true);
            sb.append(String.format(fomatString, fields[i].getName(), myField.get(myObj)));
         } catch (Exception e) {
            sb.append(PREFIX + "Can't access to field " + fields[i].getName());
         }
      }
      sb.append(LINE);
      return sb.toString();
   }

   /**
    * Return String representation of class. Each field in new line.
    * 
    * @param myObj a class for representation
    * @return
    */
   public static String toLine(Object myObj) {
      Class<?> cl = myObj.getClass();
      Field[] fields = cl.getDeclaredFields();
      StringBuilder sb = new StringBuilder();
      sb.append(cl.getSimpleName() + " [");
      for (int i = 0; i < fields.length; i++) {
         try {
            Field myField = getField(cl, fields[i].getName());
            myField.setAccessible(true);
            sb.append(fields[i].getName() + "=" + myField.get(myObj));
            if (fields.length != 1 && i < (fields.length - 1)) {
               sb.append(", ");
            }
         } catch (Exception e) {
            sb.append(PREFIX + "Can't access to the field " + fields[i].getName());
         }
      }
      sb.append("]");
      return sb.toString();
   }

   private static Field getField(Class<?> clazz, String fieldName) throws NoSuchFieldException {
      try {
         return clazz.getDeclaredField(fieldName);
      } catch (NoSuchFieldException e) {
         Class<?> superClass = clazz.getSuperclass();
         if (superClass == null) {
            throw e;
         } else {
            return getField(superClass, fieldName);
         }
      }
   }

}
