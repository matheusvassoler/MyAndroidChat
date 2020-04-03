package br.com.havebreak

import androidx.test.ext.junit.rules.ActivityScenarioRule
import br.com.havebreak.contactList.ContactListActivity
import br.com.havebreak.model.Contact
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ContactListActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(ContactListActivity::class.java)

    @Test
    fun verifyIfScreenIsVisible() {
        val contactListRobot = ContactListRobot()
        contactListRobot.checkViewIsVisible(R.id.activity_contact_list)
    }
}