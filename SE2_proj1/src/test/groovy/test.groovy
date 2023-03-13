import spock.lang.Specification
import spock.lang.Subject


class testSpec extends Specification {

    @Subject
    StringCalculator stringCalculator = new StringCalculator();

    def 'Should return 0 for empty string'() {
        given:
        String testString = ""

        expect:
        stringCalculator.calculate(testString) == 0
    }

    def 'Should return the number for a single value'() {
        given:
        String testString1 = "5"
        String testString2 = "123"

        expect:
        stringCalculator.calculate(testString1) == 5
        stringCalculator.calculate(testString2) == 123
    }

    def 'Comma delimited should return sum'() {
        given:
        String testString = "3,8"

        expect:
        stringCalculator.calculate(testString) == 11
    }

    def 'newline delimited should return sum'() {
        given:
        String testString = "2\n2"

        expect:
        stringCalculator.calculate(testString) == 4
    }

    def '3 numbers delimited should return sum'() {
        given:
        String testString1 = "2\n9\n8"
        String testString2 = "1,1\n1"

        expect:
        stringCalculator.calculate(testString1) == 19
        stringCalculator.calculate(testString2) == 3
    }

    def '3 numbers delimited should return sum'() {
        given:
        String testString = "-9"

        when:
        stringCalculator.calculate(testString)

        then:
        thrown(IllegalStateException)
    }

    def 'Numbers delimited should return sum and ignore over 1000'() {
        given:
        String testString1 = "12\n17,1,4"
        String testString2 = "12\n17,1\n1200,4"

        expect:
        stringCalculator.calculate(testString1) == 34
        stringCalculator.calculate(testString2) == 34
    }


    def 'Can add my own seprarator'() {
        given:
        String testString1 = "12\n17,1,4"
        String testString2 = "//#12\n17,1\n1200,4#3"

        expect:
        stringCalculator.calculate(testString1) == 34
        stringCalculator.calculate(testString2) == 37
    }

}