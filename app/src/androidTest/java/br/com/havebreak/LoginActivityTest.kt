package br.com.havebreak

import android.util.Log
import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.havebreak.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun verifyScreenIsVisible() {
        val loginRobot = LoginRobot()
        loginRobot.checkViewIsVisible(R.id.login_activity)
    }

    @Test
    fun verifyUsernameFieldIsVisible() {
        val loginRobot = LoginRobot()
        loginRobot.checkViewIsVisible(R.id.activity_login_edit_username)
    }

    @Test
    fun verifyPasswordFieldIsVisible() {
        val loginRobot = LoginRobot()
        loginRobot.checkViewIsVisible(R.id.activity_login_edit_password)
    }

    @Test
    fun verifyLoginButtonIsVisible() {
        val loginRobot = LoginRobot()
        loginRobot.checkViewIsVisible(R.id.activity_login_button_login)
    }

    @Test
    fun verifyUsernameFieldIsFilled() {
        val loginRobot = LoginRobot()
        loginRobot.enterTextIntoView(R.id.activity_login_edit_username, "matheus")
    }

    @Test
    fun verifyIfPasswordFieldIsFilled() {
        val loginRobot = LoginRobot()
        loginRobot.enterTextIntoView(R.id.activity_login_edit_password, "123")
    }

    @Test
    fun verifyLoginButtonClick() {
        val loginRobot = LoginRobot()
        loginRobot.clickOnView(R.id.activity_login_button_login)
    }
}