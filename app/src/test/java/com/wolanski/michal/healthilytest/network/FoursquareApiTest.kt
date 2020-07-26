package com.wolanski.michal.healthilytest.network

import com.wolanski.michal.healthilytest.entities.Response
import io.reactivex.observers.TestObserver
import org.junit.Test

class FoursquareApiTest {

    @Test
    fun emptyNearTest() {
        val testObserver = TestObserver<Response>()

        FoursquareApi.retrofitService.getVenues("")
            .subscribe(testObserver)

        testObserver.assertNotComplete()
    }

    @Test
    fun correctNearTest() {
        val testObserver = TestObserver<Response>()

        FoursquareApi.retrofitService.getVenues("London")
            .subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
    }
}