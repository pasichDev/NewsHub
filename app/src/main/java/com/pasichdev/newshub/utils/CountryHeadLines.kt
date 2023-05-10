package com.pasichdev.newshub.utils

import java.util.Locale

class CountryHeadLines {

    companion object {
        private var DEFAULT_COUNTRY = "us"

        fun getCountryHeadLines(localCountry: String): String {
            val apiCountry = arrayOf(
                "ae",
                "ar",
                "at",
                "au",
                "be",
                "bg",
                "br",
                "ca",
                "ch",
                "cn",
                "co",
                "cu",
                "cz",
                "de",
                "eg",
                "fr",
                "gb",
                "gr",
                "hk",
                "hu",
                "id",
                "ie",
                "il",
                "in",
                "it",
                "jp",
                "kr",
                "lt",
                "lv",
                "ma",
                "mx",
                "my",
                "ng",
                "nl",
                "no",
                "nz",
                "ph",
                "pl",
                "pt",
                "ro",
                "rs",
                "ru",
                "sa",
                "se",
                "sg",
                "si",
                "sk",
                "th",
                "tr",
                "tw",
                "ua",
                "us",
                "ve",
                "za"
            )
            val localCountryLowerCase = localCountry.lowercase(Locale.ROOT)

            return if (apiCountry.contains(localCountryLowerCase)) {
                localCountryLowerCase
            } else {
                DEFAULT_COUNTRY

            }
        }
    }


}
