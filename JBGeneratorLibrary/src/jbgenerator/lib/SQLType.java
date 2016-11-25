/**
* Java Bean Generator
* Ebauche pour le projet d'application 2016 
* 
*/
package jbgenerator.lib;
 
import java.sql.Types; 

/**
* Copyright (c) 2008, SQL Power Group Inc. 
* 
* This file is part of SQL Power Library. 
* 
* SQL Power Library is free software; you can redistribute it and/or modify 
* it under the terms of the GNU General Public License as published by 
* the Free Software Foundation; either version 3 of the License, or 
* (at your option) any later version. 
* 
* SQL Power Library is distributed in the hope that it will be useful, 
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
* GNU General Public License for more details. 
* 
* You should have received a copy of the GNU General Public License 
* along with this program.  If not, see <http://www.gnu.org/licenses/>.  
*/ 
 
/**
 * The SQLType class is a holder for a (name, typecode) pair.  The 
 * type codes come from java.sql.Types, which themselves come from the 
 * XOPEN specification. 
 */ 
public class SQLType { 
 
    // Constants from JDBC 4.0 which are simply copied 
    // in order to allow compilation with Java 5 
    public static final int NVARCHAR = -9; 
    public static final int NCHAR = -15; 
    public static final int NCLOB = 2011; 
 
    /**
     * The official set of types. 
     */ 
    private static final SQLType[] TYPES = { 
        new SQLType("ARRAY", 2003), 
        new SQLType("BIGINT", -5), 
        new SQLType("BINARY", -2), 
        new SQLType("BIT", -7), 
        new SQLType("BLOB", 2004), 
        new SQLType("BOOLEAN", 16), 
        new SQLType("CHAR", 1), 
        new SQLType("CLOB", 2005), 
        new SQLType("DATE", 91), 
        new SQLType("DECIMAL", 3), 
        new SQLType("DISTINCT", 2001), 
        new SQLType("DOUBLE", 8), 
        new SQLType("FLOAT", 6), 
        new SQLType("INTEGER", 4), 
        new SQLType("JAVA_OBJECT", 2000), 
        new SQLType("LONGVARBINARY", -4), 
        new SQLType("LONGVARCHAR", -1), 
        new SQLType("NULL", 0), 
        new SQLType("NUMERIC", 2), 
        new SQLType("OTHER", 1111), 
        new SQLType("REAL", 7), 
        new SQLType("REF", 2006), 
        new SQLType("SMALLINT", 5), 
        new SQLType("STRUCT", 2002), 
        new SQLType("TIME", 92), 
        new SQLType("TIMESTAMP", 93), 
        new SQLType("TINYINT", -6), 
        new SQLType("VARBINARY", -3), 
        new SQLType("VARCHAR", 12), 
        // Types from JDBC 4.0 
        new SQLType("NVARCHAR", NVARCHAR), 
        new SQLType("NCHAR", NCHAR), 
        new SQLType("NCLOB", NCLOB) 
    }; 
    
    public enum BasicSQLType { 
        /**
         * Any text or character-string data, like VARCHAR, etc 
         */ 
        TEXT, 

        /**
         * Any numerical data, including integers and floating point numbers 
         */ 
        NUMBER, 

        /**
         * Any data representing a specific date and/or time 
         */ 
        DATETIME, 

        /**
         * Any data representing a boolean value (0 or 1, true or false) 
         */ 
        BOOLEAN, 

        /**
         * Any data that does not fit the above classifications (ex. binary 
         * data) 
         */ 
        OTHER; 
    }
 /**
  * The name of this type. 
  */ 
 private String name; 
 
 /**
  * The type code of this type. 
  */ 
 private int type; 
 
 /**
  * You won't normally need to use this.  See {@link #getTypes()}. 
  */  
 public SQLType(String name, int type) { 
  this.name = name; 
  this.type = type; 
 } 
 
 /**
  * Returns a shared, static array of all defined types.  Please 
  * don't modify the returned array! 
  */ 
 public static SQLType[] getTypes() { 
  return TYPES; 
 } 
 
 public String getName() { 
  return name; 
 } 
 
 public int getType() { 
  return type; 
 } 
  
 public String toString() { 
  return name; 
 } 
 
 public static String getTypeName(int typecode) { 
  SQLType type = getType(typecode); 
  if (type == null) { 
   return "Unknown Type "+typecode; 
  } else { 
   return type.getName(); 
  } 
 } 
 
 /**
  * Returns a reference to the shared instance of SQLType that *has 
  * the requested type code.  If <code>typecode</code> is not a 
  * valid type code, returns null. 
  */ 
 public static SQLType getType(int typecode) { 
  for (int i = 0; i < TYPES.length; i++) { 
   if (TYPES[i].type == typecode) { 
    return TYPES[i]; 
   } 
  } 
  return null; 
 } 
    /**
    * Takes an int that should represent a value from {@link Types}, and 
    * converts it to a higher level basic type as represented by 
    * {@link BasicSQLType}. 
    *  
    * @param type 
    *            An int that corresponds with one of the constant values in 
    *            {@link Types}. 
    * @return A {@link BasicSQLType} that corresponds to the given 
    *         {@link Type} value 
    */ 
    public static BasicSQLType convertToBasicSQLType(int type) { 
        switch (type) { 
            case Types.BINARY: 
            case Types.BIT: 
            case Types.BOOLEAN: 
                return BasicSQLType.BOOLEAN; 

            case Types.DATE: 
            case Types.TIME: 
            case Types.TIMESTAMP: 
                return BasicSQLType.DATETIME; 

            case Types.BIGINT: 
            case Types.DECIMAL: 
            case Types.DOUBLE: 
            case Types.FLOAT: 
            case Types.INTEGER: 
            case Types.NUMERIC: 
            case Types.REAL: 
            case Types.SMALLINT: 
            case Types.TINYINT: 
                return BasicSQLType.NUMBER; 

            case Types.CHAR: 
            case Types.LONGVARCHAR: 
            case Types.VARCHAR: 
                return BasicSQLType.TEXT; 

            case Types.ARRAY: 
            case Types.BLOB: 
            case Types.CLOB: 
            case Types.DATALINK: 
            case Types.DISTINCT: 
            case Types.JAVA_OBJECT: 
            case Types.LONGVARBINARY: 
            case Types.NULL: 
            case Types.OTHER: 
            case Types.REF: 
            case Types.STRUCT: 
            case Types.VARBINARY: 
            default: 
                return BasicSQLType.OTHER;
        } 
    } 
    
    /**
    * Takes an int that should represent a valye from {@link Types}, and converts
    * it to a java class.
    * @param type An int that corresponds with one of the constant values in 
    *            {@link Types}.
    * @return A {@link Class}
    */
    public static Class convertToBasicJavaType(int type){
        switch (type) {  
            case Types.CHAR: 
            case Types.LONGVARCHAR: 
            case Types.VARCHAR: 
                return String.class; 
            case Types.NUMERIC: 
                return java.math.BigDecimal.class;
            case Types.DECIMAL: 
                return Double.class;
            case Types.SMALLINT: 
                return Short.class;
            case Types.BIT: 
                return Boolean.class;
            case Types.INTEGER: 
                return Integer.class;
            case Types.BIGINT: 
                return java.math.BigInteger.class;
            case Types.REAL: 
                return Float.class;
            case Types.FLOAT: 
                return Float.class;
            case Types.DOUBLE: 
                return Double.class;
    
            case Types.BINARY: 
            case Types.VARBINARY: 
            case Types.LONGVARBINARY: 
                return byte[].class;
                
            case Types.DATE: 
                return java.sql.Date.class;
            case Types.TIME: 
                return java.sql.Time.class;
            case Types.TIMESTAMP: 
                return java.sql.Timestamp.class;
   
            case Types.BOOLEAN: 
                return Boolean.class; 
            case Types.TINYINT: 
                return Boolean.class; 

            case Types.ARRAY: 
                return java.sql.Array.class; 
            case Types.BLOB: 
                return java.sql.Blob.class; 
            case Types.CLOB: 
                return java.sql.Clob.class; 
            case Types.STRUCT: 
                return java.sql.Struct.class; 
            case Types.REF: 
                return java.sql.Ref.class; 
            case Types.JAVA_OBJECT: 
                return java.lang.Object.class; 
                
            case Types.DATALINK: 
            case Types.DISTINCT: 
            case Types.NULL: 
            case Types.OTHER: 
            default: 
                return java.lang.Object.class; 
        } 
    }
}
