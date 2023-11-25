package com.ovais.common

import javax.inject.Inject

interface FlagResourceProvider {
    fun get(code: String): Int
}

class DefaultFlagResourceProvider @Inject constructor() : FlagResourceProvider {
    override fun get(code: String): Int {
        return when (code) {
            LanguageCodes.AF.name.lowercase() -> R.drawable.ic_south_africa

            LanguageCodes.SQ.name.lowercase() -> R.drawable.ic_albania

            LanguageCodes.AR.name.lowercase() -> R.drawable.ic_saudia_arabia

            LanguageCodes.BE.name.lowercase() -> R.drawable.ic_russia

            LanguageCodes.BG.name.lowercase() -> R.drawable.ic_bulgaria

            LanguageCodes.BN.name.lowercase() -> R.drawable.ic_bangladesh

            LanguageCodes.CA.name.lowercase() -> R.drawable.ic_andorra

            LanguageCodes.ZH.name.lowercase() -> R.drawable.ic_china

            LanguageCodes.HR.name.lowercase() -> R.drawable.ic_bosnia

            LanguageCodes.CS.name.lowercase() -> R.drawable.ic_czech

            LanguageCodes.DA.name.lowercase() -> R.drawable.ic_denmark

            LanguageCodes.NL.name.lowercase() -> R.drawable.ic_netherland

            LanguageCodes.EN.name.lowercase() -> R.drawable.ic_united_states

            LanguageCodes.EO.name.lowercase() -> R.drawable.ic_brazil

            LanguageCodes.FI.name.lowercase() -> R.drawable.ic_finnland

            LanguageCodes.FR.name.lowercase() -> R.drawable.ic_france

            LanguageCodes.GL.name.lowercase() -> R.drawable.ic_spain
            LanguageCodes.KA.name.lowercase() -> R.drawable.ic_abkhazia

            LanguageCodes.DE.name.lowercase() -> R.drawable.ic_germany


            LanguageCodes.EL.name.lowercase() -> R.drawable.ic_greece

            LanguageCodes.GU.name.lowercase() -> R.drawable.ic_india

            LanguageCodes.HT.name.lowercase() -> R.drawable.ic_haiti

            LanguageCodes.HE.name.lowercase() -> R.drawable.ic_palestine
            LanguageCodes.HI.name.lowercase() -> R.drawable.ic_india

            LanguageCodes.HU.name.lowercase() -> R.drawable.ic_hungary

            LanguageCodes.IS.name.lowercase() -> R.drawable.ic_iceland

            LanguageCodes.ID.name.lowercase() -> R.drawable.ic_indonesia

            LanguageCodes.GA.name.lowercase() -> R.drawable.ic_ireland

            LanguageCodes.IT.name.lowercase() -> R.drawable.ic_italy

            LanguageCodes.JA.name.lowercase() -> R.drawable.ic_japan

            LanguageCodes.KN.name.lowercase() -> R.drawable.ic_india


            LanguageCodes.KO.name.lowercase() -> R.drawable.ic_korea
            LanguageCodes.LT.name.lowercase() -> R.drawable.ic_lithuania

            LanguageCodes.LV.name.lowercase() -> R.drawable.ic_latvia

            LanguageCodes.MK.name.lowercase() -> R.drawable.ic_macedonia

            LanguageCodes.MR.name.lowercase() -> R.drawable.ic_india

            LanguageCodes.MS.name.lowercase() -> R.drawable.ic_malaysia

            LanguageCodes.MT.name.lowercase() -> R.drawable.ic_malta

            LanguageCodes.NO.name.lowercase() -> R.drawable.ic_norway

            LanguageCodes.FA.name.lowercase() -> R.drawable.ic_iran

            LanguageCodes.PL.name.lowercase() -> R.drawable.ic_poland

            LanguageCodes.PT.name.lowercase() -> R.drawable.ic_portugal

            LanguageCodes.RO.name.lowercase() -> R.drawable.ic_romania

            LanguageCodes.RU.name.lowercase() -> R.drawable.ic_russia

            LanguageCodes.SK.name.lowercase() -> R.drawable.ic_slovakia


            LanguageCodes.SL.name.lowercase() -> R.drawable.ic_slovenia

            LanguageCodes.ES.name.lowercase() -> R.drawable.ic_spain

            LanguageCodes.SV.name.lowercase() -> R.drawable.ic_swedan

            LanguageCodes.SW.name.lowercase() -> R.drawable.ic_tanzania

            LanguageCodes.TL.name.lowercase() -> R.drawable.ic_philippines

            LanguageCodes.TA.name.lowercase() -> R.drawable.ic_india

            LanguageCodes.TE.name.lowercase() -> R.drawable.ic_india


            LanguageCodes.TH.name.lowercase() -> R.drawable.ic_thailand

            LanguageCodes.TR.name.lowercase() -> R.drawable.ic_turkey

            LanguageCodes.UK.name.lowercase() -> R.drawable.ic_ukraine

            LanguageCodes.UR.name.lowercase() -> R.drawable.ic_pakistan

            LanguageCodes.VI.name.lowercase() -> R.drawable.ic_veitnam

            LanguageCodes.CY.name.lowercase() -> R.drawable.ic_wales

            else -> R.drawable.ic_flag
        }
    }
}