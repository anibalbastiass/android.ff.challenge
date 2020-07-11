package com.ff.challenge.app.data.cache

import android.content.SharedPreferences
import com.ff.challenge.library.testutils.foundation.RandomFactory
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test

class FFSharedPreferenceTest {

    private val sharedPreference = mock<SharedPreferences>()
    private val editor = mock<SharedPreferences.Editor>()
    private val depositSharedPreference = FFSharedPreference(sharedPreference)

    @Before
    fun setUp() {
        stubSharedPreferenceEditor()
        stubSharedPreferencePutString()
        stubSharedPreferencePutBoolean()
        stubSharedPreferenceClear()
    }

    private fun stubSharedPreferenceEditor() {
        whenever(sharedPreference.edit()).thenReturn(editor)
    }

    private fun stubSharedPreferencePutString() {
        whenever(editor.putString(any(), any())).thenReturn(editor)
    }

    private fun stubSharedPreferencePutBoolean() {
        whenever(editor.putBoolean(any(), any())).thenReturn(editor)
    }

    private fun stubSharedPreferenceClear() {
        whenever(editor.clear()).thenReturn(editor)
    }

    @Test
    fun `given Stub SharedPreference Put String when Put Data then Apply`() {
        stubSharedPreferencePutString()
        depositSharedPreference.putData(
            RandomFactory.generateString(),
            RandomFactory.generateString()
        )
        verify(editor, times(1)).apply()
    }

    @Test
    fun `given StubSharedPreferenceClear when ClearData then Apply`() {
        stubSharedPreferenceClear()
        depositSharedPreference.clearData()
        verify(editor, times(1)).apply()
    }

    @Test
    fun `given Boolean, when putData, then verify putBoolean`() {
        depositSharedPreference.putData(
            RandomFactory.generateString(),
            RandomFactory.generateBoolean()
        )
        verify(editor, times(1)).putBoolean(any(), any())
    }

    @Test
    fun `when getBoolean, then verify getBoolean`() {
        depositSharedPreference.getBoolean(RandomFactory.generateString())
        verify(sharedPreference, times(1)).getBoolean(any(), any())
    }
}