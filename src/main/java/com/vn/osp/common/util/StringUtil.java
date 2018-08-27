package com.vn.osp.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static String removeSpecialCharactersNotSpace(String value) {
        String ALPHANUMERIC = "[^\\p{L}\\p{N} ]+";
        try {
            return value.replaceAll(ALPHANUMERIC, "");
        } catch (Exception var2) {
            return null;
        }
    }
    public static String removeSpecialCharactersNotHTML(String value) {
        String ALPHANUMERIC = "[^\\p{L}\\p{N} ~`!@#$%^&*()_=+{}<>\"/;=&:\\-\\[\\]\t\\\\°C≥≤≡≠×÷±→?.,|«©®£¨»½¼¾ǂʼʾʿˀˁ˅ΣΞΘΔθπФ‰†‡⅓⅔⅕⅖⅗⅘⅙⅚⅛⅜⅝⅞⅟―Γʽ←↑→↓↔↕←↑→↓↔↕↖↗↘↙↚↛↜↝↞↟↠↡↢↣↤↥↦↧↨↩↪↫↬↭↮↯↰↱↲↳↴↵↶↷↸↹↺↻↼↽↾↿⇀⇁⇂⇃⇄⇅⇆⇇⇈⇉⇊⇋⇌⇍⇎⇏⇐⇑⇒⇓⇔⇕⇖⇗⇘⇙⇚⇛⇜⇝⇞⇟⇠⇡⇢⇣⇤⇥⇦⇧⇨⇩⇪⇫⇬⇭⇮⇯⇰⇱⇲⇳⇴⇵⇶⇷⇸⇹⇺⇻⇼⇽⇾⇿∀∁∂∃∄∅∆∇∈∉∊∋∌∍∎∏∐∑−∓∔∕∖∗∘∙√∛∜∝∞∟∠∡∢∣∤∥∦∧∨∩∪∫∬∭∮∯∰∱∲∳∴∵∶∷∸∹∺∻∼∽∾∿≀≁≂≃≄≅≆≇≈≉≊≋≌≍≎≏≐≑≒≓≔≕≖≗≘≙≚≛≜≝≞≟≠≡≢≣≤≥≦≧≨≩≪≫≬≭≮≯≰≱≲≳≴≵≶≷≸≹≺≻≼≽≾≿⊀⊁⊂⊃⊄⊅⊆⊇⊈⊉⊊⊋⊌⊍⊎⊏⊐⊑⊒⊓⊔⊕⊖⊗⊘⊙⊚⊛⊜⊝⊞⊟⊠⊡⊢⊣⊤⊥⊦⊧⊨⊩⊪⊫⊬⊭⊮⊯⊰⊱⊲⊳⊴⊵⊶⊷⊸⊹⊺⊻⊼⊽⊾⊿⋀⋁⋂⋃⋄⋅⋆⋇⋈⋉⋊⋋⋌⋍⋎⋏⋐⋑⋒⋓⋔⋕⋖⋗⋘⋙⋚⋛⋜⋝⋞⋟⋠⋡⋢⋣⋤⋥⋦⋧⋨⋩⋪⋫⋬⋭⋮⋯⋰⋱⋲⋳⋴⋵⋶⋷⋸⋹⋺⋻⋼⋽⋾⋿]+";
        try {
            return value.replaceAll(ALPHANUMERIC, "");
        } catch (Exception var2) {
            return null;
        }
    }
    public static boolean checkSpecialCharacter(String value) {
        String ALPHANUMERIC = "^[\\p{L}\\p{N} ]+";
        try {
            Pattern p = Pattern.compile(ALPHANUMERIC, Pattern.CANON_EQ | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher m = p.matcher(value);
            return m.matches();
        } catch (Exception var2) {
            return false;
        }
    }
}
