package jp.co.spiralinks.horizon.utils;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * ＣＳＶファイル出力
 */
public class CsvWriter implements AutoCloseable {

    private OutputStream _st = null;

    private boolean _bom = false;

    /**
     * 行区切り
     */
    @Setter
    @Getter
    private String lineDelimiter = System.getProperty("line.separator");

    /**
     * 区切り文字
     */
    @Setter
    @Getter
    private String separator = ",";

    /**
     * エンコード
     */
    @Setter
    @Getter
    private String encode = "UTF-8";

    /**
     * 囲み処理の要否
     */
    @Setter
    @Getter
    private boolean enclosureRequired = false;

    /**
     * 囲み文字
     */
    @Getter
    private String enclosure = "\"";

    /**
     * 初回書き込み
     */
    private boolean isFirstPosition = true;

    private CsvWriter(String filename, boolean append, boolean bom) {
        this._bom = bom;
        try {
            this._st = new FileOutputStream(filename, append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CsvWriter(String filename, boolean bom) throws IOException {
        this(filename, false, bom);
    }

    public CsvWriter(String filename) throws IOException {
        this(filename, false, true);
    }

    public CsvWriter(OutputStream st) throws IOException {
        this(st, true);
    }

    public CsvWriter(OutputStream st, boolean bom) throws IOException {
        this._st = st;
        this._bom = bom;
    }

    @Override
    public void close() throws Exception {
        if (this._st != null) {
            this._st.close();
            this._st = null;
        }
    }

    /**
     * 指定された文字列配列を１行にして出力します。
     * 先頭行の場合のみ、コンストラクタでＢＯＭ指定されエンコードがＵＴＦ-８であればＢＯＭを出力します
     *
     * @param items 出力項目配列
     */
    public void writeLine(List<String> items) {

        try {
            if (this._bom && this.IsStartPosition() && "UTF-8".equals(this.encode)) {
                this._st.write(0xef);
                this._st.write(0xbb);
                this._st.write(0xbf);
            }

            List<String> newItems = new ArrayList<>();
            for (String item : items) {
                newItems.add(itemConvert(item));
            }

            String line = String.join(this.separator, newItems).concat(this.lineDelimiter);
            this._st.write(line.getBytes(this.encode));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 出力データクラスを出力ラムダ式に従いを１行にして出力します。
     *
     * @param entity 出力データクラス
     * @param items  出力処理ラムダ式配列
     * @param <T>    出力データクラス型
     */
    public <T> void writeLine(T entity, List<Function<T, String>> items) {
        List<String> newItems = new ArrayList<>();
        for (Function<T, String> item : items) {
            newItems.add(item.apply(entity));
        }
        this.writeLine(newItems);
    }

    private String itemConvert(String src) {

        String item = src;
        if( item == null ){
            item = StringUtils.EMPTY;
        }

        //「"」を「""」に変換する
        String str = item.replaceAll(this.enclosure, this.enclosure + this.enclosure);

        //囲み文字必須、文字列が変換されている、改行、区切り文字を含む場合は囲み文字で囲む
        if (this.enclosureRequired
                || !str.equals(item)
                || str.contains("\r\n") || str.contains("\n")
                || str.contains(this.separator)) {
            str = String.format("%s%s%s", this.enclosure, str, this.enclosure);
        }

        return str;
    }

    private boolean IsStartPosition() {
        boolean result = isFirstPosition;
        isFirstPosition = false;
        return result;
    }

}
