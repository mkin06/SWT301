package tests;

import org.junit.jupiter.api.Assertions;
import pages.RegistrationPage;
import org.junit.jupiter.api.Test;

public class RegistrationTest extends BaseTest {
    // TC01: Đăng ký thành công
    @Test
    void TC01_registerSuccess() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .selectDate(".react-datepicker__day--010:not(.react-datepicker__day--outside-month)")
                .enterSubject("Maths")
                .selectHobby("hobbies-checkbox-2")
                .uploadPicture("test_image.png")
                .enterAddress("123 Vo Van Kiet")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isModalVisible());
    }

    // TC02: First Name trống
    @Test
    void TC02_emptyFirstName() {
        new RegistrationPage(driver)
                .open()
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFormNotSubmitted());
    }

    // TC03: Last Name trống
    @Test
    void TC03_emptyLastName() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("Last Name"));
    }

    // TC04: Email sai định dạng
    @Test
    void TC04_invalidEmail() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@@example")
                .selectGenderMale()
                .enterMobile("0987654321")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("Email"));
    }

    // TC05: Mobile không đủ 10 số
    @Test
    void TC05_invalidMobileShort() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("12345")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("Mobile"));
    }

    // TC06: Không chọn Gender
    @Test
    void TC06_missingGender() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .enterMobile("0987654321")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("Gender"));
    }

    // TC07: Không nhập Subject
    @Test
    void TC07_missingSubject() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("Subjects"));
    }

    // TC08: Upload file sai định dạng
    @Test
    void TC08_uploadPictureSuccess() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .uploadPicture("test_image.png")
                .enterAddress("Da Nang")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isModalVisible());
    }


    // TC09: Không chọn State
    @Test
    void TC09_missingState() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .enterAddress("123 Vo Van Kiet")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("State"));
    }

    // TC10: Chọn State nhưng không chọn City
    @Test
    void TC10_missingCity() {
        new RegistrationPage(driver)
                .open()
                .enterFirstName("Kien")
                .enterLastName("Le")
                .enterEmail("kien@example.com")
                .selectGenderMale()
                .enterMobile("0987654321")
                .enterAddress("123 Vo Van Kiet")
                .selectState("NCR")
                .submit();

        Assertions.assertTrue(new RegistrationPage(driver).isFieldError("City"));
    }
}
