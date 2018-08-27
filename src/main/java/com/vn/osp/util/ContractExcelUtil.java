package com.vn.osp.util;

import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 * <P>Contract Excel Util</P>
 *
 * @author HungPT
 * @version $Revision: 19144 $
 */
public class ContractExcelUtil {

    public static final Integer FONT_HEIGHT_NORMAL = 11;
    public static final Integer FONT_HEIGHT_TITLE = 14;
    public static final String TIMES_NEWS_ROMAN = "Times New Roman";
    public static final String CURRENCY_FORMAT = "#,##0";
    public static final Double NARROW_MARGIN_TOP = 0.75;
    public static final Double NARROW_MARGIN_RIGHT = 0.25;
    public static final Double NARROW_MARGIN_BOTTOM = 0.75;
    public static final Double NARROW_MARGIN_LEFT = 0.25;
    
    public static final Double NORMAL_MARGIN_TOP = 0.75;
    public static final Double NORMAL_MARGIN_RIGHT = 0.7;
    public static final Double NORMAL_MARGIN_BOTTOM = 0.75;
    public static final Double NORMAL_MARGIN_LEFT = 0.7;
    
    public static final Integer UNITS_OF_CHAR = 256;
    public static final String DOT_SPACE = ".......";
    
    private HashMap<StyleKind, HSSFCellStyle> cellStyleList;

    /** Cell type styles */
    public enum StyleKind {

        PAGE_TITLE,

        NORMAL_ITEM_LEFT,

        NORMAL_ITEM,

        NORMAL_ITEM_RIGHT,

        TABLE_TITLE,

        TABLE_ITEM_LEFT,
        
        TABLE_ITEM_CENTER,
        
        TABLE_ITEM_RIGHT,
        
        TABLE_SUB_ITEM_LEFT,
        
        TABLE_SUB_ITEM_CENTER,
        
        TABLE_FIRST_SUB_ITEM_LEFT,
        
        TABLE_FIRST_SUB_ITEM_CENTER
    }
    
    public ContractExcelUtil() {
        cellStyleList = new HashMap<StyleKind, HSSFCellStyle>();
    }

    /**
     * <p>Get cell style</p>
     *
     * @param workbook
     * @param kind
     * @return HSSFCellStyle
     */
    public HSSFCellStyle getCellStyle(HSSFWorkbook workbook, StyleKind kind) {
        
        HSSFCellStyle style = null;
        
        if (kind.equals(StyleKind.PAGE_TITLE)) {
            
            if (cellStyleList.containsKey(StyleKind.PAGE_TITLE)) {
                return cellStyleList.get(StyleKind.PAGE_TITLE);
            } else {
                style = workbook.createCellStyle();
            
                style.setFont(createFont(workbook, FONT_HEIGHT_TITLE, HSSFFont.BOLDWEIGHT_BOLD));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                cellStyleList.put(StyleKind.PAGE_TITLE, style);
            }
        } else if (kind.equals(StyleKind.NORMAL_ITEM)) {
            if (cellStyleList.containsKey(StyleKind.NORMAL_ITEM)) {
                return cellStyleList.get(StyleKind.NORMAL_ITEM);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                
                cellStyleList.put(StyleKind.NORMAL_ITEM, style);
            }

        } else if (kind.equals(StyleKind.NORMAL_ITEM_LEFT)) {

            if (cellStyleList.containsKey(StyleKind.NORMAL_ITEM_LEFT)) {
                return cellStyleList.get(StyleKind.NORMAL_ITEM_LEFT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                
                cellStyleList.put(StyleKind.NORMAL_ITEM_LEFT, style);
            }

        } else if (kind.equals(StyleKind.NORMAL_ITEM_RIGHT)) {

            if (cellStyleList.containsKey(StyleKind.NORMAL_ITEM_RIGHT)) {
                return cellStyleList.get(StyleKind.NORMAL_ITEM_RIGHT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                
                cellStyleList.put(StyleKind.NORMAL_ITEM_RIGHT, style);
            }

        } else if (kind.equals(StyleKind.TABLE_TITLE)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_TITLE)) {
                return cellStyleList.get(StyleKind.TABLE_TITLE);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_BOLD));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                cellStyleList.put(StyleKind.TABLE_TITLE, style);
            }

        } else if (kind.equals(StyleKind.TABLE_ITEM_LEFT)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_ITEM_LEFT)) {
                return cellStyleList.get(StyleKind.TABLE_ITEM_LEFT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                cellStyleList.put(StyleKind.TABLE_ITEM_LEFT, style);
            }

        } else if (kind.equals(StyleKind.TABLE_ITEM_CENTER)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_ITEM_CENTER)) {
                return cellStyleList.get(StyleKind.TABLE_ITEM_CENTER);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                cellStyleList.put(StyleKind.TABLE_ITEM_CENTER, style);
            }

        } else if (kind.equals(StyleKind.TABLE_ITEM_RIGHT)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_ITEM_RIGHT)) {
                return cellStyleList.get(StyleKind.TABLE_ITEM_RIGHT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                cellStyleList.put(StyleKind.TABLE_ITEM_RIGHT, style);
            }
        } else if (kind.equals(StyleKind.TABLE_SUB_ITEM_LEFT)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_SUB_ITEM_LEFT)) {
                return cellStyleList.get(StyleKind.TABLE_SUB_ITEM_LEFT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
                style.setBorderBottom(HSSFCellStyle.NO_FILL);
                
                cellStyleList.put(StyleKind.TABLE_SUB_ITEM_LEFT, style);
            }
        } else if (kind.equals(StyleKind.TABLE_SUB_ITEM_CENTER)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_SUB_ITEM_CENTER)) {
                return cellStyleList.get(StyleKind.TABLE_SUB_ITEM_CENTER);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
                style.setBorderBottom(HSSFCellStyle.NO_FILL);
                
                cellStyleList.put(StyleKind.TABLE_SUB_ITEM_CENTER, style);
            }
        } else if (kind.equals(StyleKind.TABLE_FIRST_SUB_ITEM_LEFT)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_FIRST_SUB_ITEM_LEFT)) {
                return cellStyleList.get(StyleKind.TABLE_FIRST_SUB_ITEM_LEFT);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_NORMAL));
                style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                style.setBorderBottom(HSSFCellStyle.NO_FILL);
                
                cellStyleList.put(StyleKind.TABLE_FIRST_SUB_ITEM_LEFT, style);
            }
        } else if (kind.equals(StyleKind.TABLE_FIRST_SUB_ITEM_CENTER)) {

            if (cellStyleList.containsKey(StyleKind.TABLE_FIRST_SUB_ITEM_CENTER)) {
                return cellStyleList.get(StyleKind.TABLE_FIRST_SUB_ITEM_CENTER);
            } else {
                style = workbook.createCellStyle();
                
                style.setFont(createFont(workbook, FONT_HEIGHT_NORMAL, HSSFFont.BOLDWEIGHT_BOLD));
                style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                style.setWrapText(true);
                drawBorder(style, HSSFCellStyle.BORDER_THIN, HSSFColor.BLACK.index);
                
                style.setBorderBottom(HSSFCellStyle.NO_FILL);
                
                cellStyleList.put(StyleKind.TABLE_FIRST_SUB_ITEM_CENTER, style);
            }
        } 

        return style;
    }


    /**
     * <p>Create font</p>
     *
     * @param workbook
     * @param fontHeight
     * @param bold
     * @return HSSFFont
     */
    public HSSFFont createFont(HSSFWorkbook workbook, int fontHeight, short bold) {
        for (short i = 0; i < workbook.getNumberOfFonts(); i++) {
            HSSFFont font = workbook.getFontAt(i);
            
            if (TIMES_NEWS_ROMAN.equals(font.getFontName()) && font.getFontHeightInPoints() == (short) fontHeight
                    && font.getBoldweight() == bold) {
                return font;
            }
        }
        
        HSSFFont font = workbook.createFont();
        
        font.setFontHeightInPoints((short) fontHeight);
        font.setFontName(TIMES_NEWS_ROMAN);
        font.setBoldweight(bold);
        return font;
    }
    
    /**
     * Set default font for workbook
     * 
     * @param workbook
     */
    public void setDefaultFont(HSSFWorkbook workbook) {
        for (short i = 0; i < workbook.getNumberOfFonts(); i++) {
            HSSFFont font = workbook.getFontAt(i);
            font.setFontName(TIMES_NEWS_ROMAN);
            font.setFontHeightInPoints(FONT_HEIGHT_NORMAL.shortValue());
        }
    }


    /**
     * <p>Draw border</p>
     *
     * @param style
     * @param borderStyle
     * @param borderColor
     */
    public void drawBorder(HSSFCellStyle style, short borderStyle, short borderColor) {
        style.setBorderBottom(borderStyle);
        style.setBottomBorderColor(borderColor);
        style.setBorderLeft(borderStyle);
        style.setLeftBorderColor(borderColor);
        style.setBorderRight(borderStyle);
        style.setRightBorderColor(borderColor);
        style.setBorderTop(borderStyle);
        style.setTopBorderColor(borderColor);
    }

    /**
     * <p>Create cell</p>
     *
     * @param wb
     * @param sheet
     * @param rowIndex
     * @param columnIndex
     * @param style
     * @param value
     * @param formula
     * @return HSSFCell
     */
    public HSSFCell createCell(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex,
            int columnIndex, HSSFCellStyle style, String value, boolean formula) {

        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.createCell(columnIndex);

        if (formula) {
            cell.setCellFormula(value);
        } else {
            HSSFRichTextString richTextValue = new HSSFRichTextString(value);
            cell.setCellValue(richTextValue);
        }

        if (style != null) {
            cell.setCellStyle(style);
        }

        return cell;
    }

    /**
     * <p>Append rich text to cell</p>
     *
     * @return HSSFCell
     */
    public HSSFCell createCell(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex,
            int columnIndex, HSSFCellStyle style, HSSFRichTextString value) {

        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        if (style != null) {
            cell.setCellStyle(style);
        }

        cell.setCellValue(value);

        return cell;
    }


    /**
     *
     * <p>Create cell</p>
     *
     * @param wb
     * @param sheet
     * @param rowIndex
     * @param columnIndex
     * @param style
     * @param value
     * @return HSSFCell
     */
    public HSSFCell createCell(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex,
            int columnIndex, HSSFCellStyle style, Double value) {

        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.createCell(columnIndex);

        cell.setCellValue(value);

        if (style != null) {
            cell.setCellStyle(style);
        }

        return cell;
    }
}
