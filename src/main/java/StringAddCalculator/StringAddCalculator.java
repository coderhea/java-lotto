package StringAddCalculator;

import java.util.regex.Matcher;

import static StringAddCalculator.utils.Validations.CheckDelimiter.isInvalidDelimiter;
import static StringAddCalculator.utils.Validations.CheckNumber.isANumber;
import static StringAddCalculator.utils.Validations.CheckNumber.isMinus;
import static StringAddCalculator.utils.Validations.CheckString.isNullOrBlank;
import static StringAddCalculator.utils.Constants.*;

public class StringAddCalculator {
    // 음? 커스텀구분자로 m.group(2).split(cus)한 후에는 각 구분자 맞는지 확인할 필요는 없나? 확인하려고 하면 에러발생
    // if (isInvalidCustomDelimiter(nextExpressions[i], customDelimiter)) { return result2;}

    public static int splitAndSum(String expression) throws NotANumberException, NegativeNumberException {
        if (isNullOrBlank(expression)) {
            return ADD_NULL_OR_BLANK;
        }
        Matcher m = COMPILER_CUSTOM_DELIMITER.matcher(expression);
        if (m.find()) {
            String customDelimiter = m.group(CUSTOM_DELIMITER_INDEX);
            String[] afterCustomDelimiter = m.group(AFTER_CUSTOM_DELIMITER_INDEX).split(customDelimiter);
            return sumWhenCustomDelimiter(afterCustomDelimiter);
        }
        return sumWhenOriginalDelimiter(expression);
    }

    public static int sumWhenOriginalDelimiter(String expression) throws NotANumberException, NegativeNumberException{
        String[] expressions = toSplits(expression);
        int result = findNumber(expressions, 0);
        for (int i = 1; i < expressions.length; i += 2) {
            if (isInvalidDelimiter(expressions[i])) {
                return result;
            }
            result = result + findNumber(expressions, i + 1);
        }
        return result;
    }

    public static int sumWhenCustomDelimiter(String[] expressions) throws NotANumberException, NegativeNumberException{
        int result = findNumber(expressions, 0);
        for (int i = 1; i < expressions.length; i ++) {
            result = result + findNumber(expressions, i);;
        }
        return result;
    }

    public static int findNumber(String[] expressions, int i) throws NotANumberException, NegativeNumberException {
        int result = toInt(expressions[i]);
        if (!isANumber(result)) {
            throw new NotANumberException(ERROR_NOT_A_NUMBER);
        }
        if (isMinus(result)) {
            throw new NegativeNumberException(ERROR_NEGATIVE_NUMBER);
        }
        return result;
    }

    public static int toInt(String expression) {
        return Integer.parseInt(expression);
    }

    public static String[] toSplits(String expression) {
        if (isNullOrBlank(expression)) {
            expression = RESULT_FOR_NONE_INPUT;
        }
        String[] expressions = expression.split("");
        return expressions;
    }
}
