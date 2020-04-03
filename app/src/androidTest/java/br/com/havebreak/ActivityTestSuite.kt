package br.com.havebreak

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ContactListActivityTest::class,
    LoginActivityTest::class
)
class ActivityTestSuite {
}