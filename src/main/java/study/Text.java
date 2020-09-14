package study;

import java.util.EnumSet;
import java.util.Set;

public class Text {
    public enum Style {
        BOLD, ITALIC, UNDERLINE
    }
    //EnumSet 아닌 Set으로 사용가능하게 함

    public void applyStles(Set<Style> styles) {
        //Body goes here
    }

    public static void main(String[] args) {
        Text text = new Text();

        text.applyStles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
//http://rangken.github.io/blog/2015/effective-java-5/