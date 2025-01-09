package co.sublimetech.lideres.statistics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.sublimetech.lideres.core.design_system.CustomDropdown
import co.sublimetech.lideres.core.design_system.CustomHeader
import co.sublimetech.lideres.core.design_system.CustomLoader
import co.sublimetech.lideres.core.design_system.theme.LighterGray
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.adult
import lideres.composeapp.generated.resources.ageTitle
import lideres.composeapp.generated.resources.attack
import lideres.composeapp.generated.resources.bisexual
import lideres.composeapp.generated.resources.blindness
import lideres.composeapp.generated.resources.boy_and_girl
import lideres.composeapp.generated.resources.caretaker
import lideres.composeapp.generated.resources.civil
import lideres.composeapp.generated.resources.communal
import lideres.composeapp.generated.resources.different_identifying_trait_title
import lideres.composeapp.generated.resources.disability_type_title_2
import lideres.composeapp.generated.resources.elderly
import lideres.composeapp.generated.resources.extortion
import lideres.composeapp.generated.resources.family_member_homicide
import lideres.composeapp.generated.resources.farmer
import lideres.composeapp.generated.resources.father
import lideres.composeapp.generated.resources.female
import lideres.composeapp.generated.resources.futura_md_bt
import lideres.composeapp.generated.resources.genderTitle
import lideres.composeapp.generated.resources.guild
import lideres.composeapp.generated.resources.hearing
import lideres.composeapp.generated.resources.heterosexual
import lideres.composeapp.generated.resources.homosexual
import lideres.composeapp.generated.resources.human_rights_watch
import lideres.composeapp.generated.resources.intellectual
import lideres.composeapp.generated.resources.intersexual
import lideres.composeapp.generated.resources.kidnapping
import lideres.composeapp.generated.resources.little_people
import lideres.composeapp.generated.resources.male
import lideres.composeapp.generated.resources.men
import lideres.composeapp.generated.resources.mental
import lideres.composeapp.generated.resources.mother
import lideres.composeapp.generated.resources.multiple
import lideres.composeapp.generated.resources.no
import lideres.composeapp.generated.resources.organization_membership
import lideres.composeapp.generated.resources.other
import lideres.composeapp.generated.resources.people_under_care
import lideres.composeapp.generated.resources.recruitment
import lideres.composeapp.generated.resources.rural
import lideres.composeapp.generated.resources.sexTitle
import lideres.composeapp.generated.resources.sexual_orientationTitle
import lideres.composeapp.generated.resources.teenager
import lideres.composeapp.generated.resources.threat
import lideres.composeapp.generated.resources.threat_means_short
import lideres.composeapp.generated.resources.transgender
import lideres.composeapp.generated.resources.urban
import lideres.composeapp.generated.resources.victims
import lideres.composeapp.generated.resources.visual
import lideres.composeapp.generated.resources.women
import lideres.composeapp.generated.resources.yes
import lideres.composeapp.generated.resources.young_adult
import org.example.project.charts.HorizontalBarChart
import org.example.project.charts.PieChartCard
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun StatisticsScreenRoot(
    viewModel: StatisticsViewModel = koinViewModel(),
    onStatisticsClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()


    StatisticsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is StatisticsAction.OnFormClick -> onStatisticsClick()
                else -> {}
            }
            viewModel.onAction(action)
        },
    )
}


@Composable
fun StatisticsScreen(
    state: StatisticsState,
    onAction: (StatisticsAction) -> Unit,
) {

    var selectedFilterValue by remember { mutableStateOf("Siempre") }


    LaunchedEffect(Unit) {
        onAction(StatisticsAction.OnGetForms)
    }

    if (state.loading) {
        CustomLoader()

    } else {

        Scaffold(
            topBar = {
                CustomHeader(
                    "Estadísticas",
                    false,
                    { onAction(StatisticsAction.OnFormClick) })
            },
            content = {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)

                ) {

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 18.dp)
                            .padding(top = 40.dp, bottom = 16.dp)
                    ) {
                        Spacer(Modifier.height(64.dp))
                        Text(
                            "Formularios Registrados",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(Res.font.futura_md_bt)
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = state.fetchedForms.count().toString(),
                                color = Color.Black,
                                fontSize = 30.sp,
                                fontFamily = FontFamily(
                                    Font(Res.font.futura_md_bt)
                                )
                            )

                            Spacer(modifier =Modifier.weight(1f))

                            CustomDropdown(
                                selectedValue = selectedFilterValue,
                                onSelectedValueChange = { newValue ->
                                    selectedFilterValue = newValue
                                }
                            )
                        }
                    }

                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(1),
                        modifier = Modifier.background(LighterGray),
                        verticalItemSpacing = 16.dp,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {


                        item {
                            HorizontalBarChart(
                                title = "Distribución por Tipo de Riesgo o Amenaza",
                                dataSets = getRiskOrThreatDataSet(state, selectedFilterValue,),
                            )
                        }

                        item {
                            PieChartCard(
                                title = "Zona de Domicilio",
                                data = getAddressZoneDataSet(state, selectedFilterValue),
                            )
                        }

                        item {
                            HorizontalBarChart(
                                title = "Perfil Demográfico",
                                dataSets = getDemographicProfileDataSet(state, selectedFilterValue),
                            )
                        }

                        item {
                            HorizontalBarChart(
                                title = "Discapacidad y Otros Factores Diferenciales",
                                dataSets = disabilitiesAndIdentifyingTraitFactorDataSet(
                                    state, selectedFilterValue
                                ),
                            )
                        }

                        item {
                            HorizontalBarChart(
                                title = "Afiliación a Organizaciones",
                                dataSets = getOrganizationAffiliationDataSet(state, selectedFilterValue),
                            )
                        }

                        item {
                            PieChartCard(
                                title = "Medidas de Protección Existentes",
                                data = getProvisionalMeasureDataSet(state, selectedFilterValue),
                            )
                        }

                        // item {
                        //     Row(modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)) {
                        //         Spacer(modifier = Modifier.weight(1f))
                        //         Button(
                        //             shape = RoundedCornerShape(8.dp),
                        //             colors = ButtonColors(
                        //                 containerColor = MaterialTheme.colorScheme.primary,
                        //                 contentColor = White,
                        //                 disabledContainerColor = MaterialTheme.colorScheme.outline,
                        //                 disabledContentColor = MaterialTheme.colorScheme.outline
                        //             ),
                        //             onClick = { onAction(StatisticsAction.OnFormClick) }
                        //         ) {
                        //             Text(
                        //                 "Crear Formulario",
                        //                 color = White,
                        //                 fontSize = 14.sp,
                        //                 fontFamily = FontFamily(
                        //                     Font(Res.font.futura_md_bt)
                        //                 )
                        //             )
                        //         }
                        //     }
                        // }
                    }
                }
            }
        )
    }
}


fun String.toLocalDateFromDdMmYyyy(): LocalDate? {

    return try {
        val (day, month, year) = this.split("/").map { it.toInt() }
        LocalDate(year, month, day)
    } catch (e: Exception) {
        null
    }
}


fun getDateRange(option: String): Pair<LocalDate, LocalDate> {
    val today = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault()).date
    return when (option) {
        "Últimos 7 días" -> {
            val startDate = today.minus(7, DateTimeUnit.DAY )
            startDate to today
        }
        "Últimos 30 días" -> {
            val startDate = today.minus(30, DateTimeUnit.DAY )
            startDate to today
        }
        "Siempre" -> {
            LocalDate(1900, 1, 1) to today
        }
        else -> {
            LocalDate(1900, 1, 1) to today
        }
    }
}


@Composable
fun getRiskOrThreatDataSet(
    state: StatisticsState,
    filterOption: String,
): Map<String, List<Triple<String, Double, Color>>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.riskSituationType == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }


    val knownRisks = setOf(
        stringResource(Res.string.threat),
        stringResource(Res.string.attack),
        stringResource(Res.string.kidnapping),
        stringResource(Res.string.family_member_homicide),
        stringResource(Res.string.extortion),
        stringResource(Res.string.recruitment)
    )


    val threatCount = countOccurrence(stringResource(Res.string.threat))
    val attackCount = countOccurrence(stringResource(Res.string.attack))
    val kidnappingCount = countOccurrence(stringResource(Res.string.kidnapping))
    val familyHomicideCount = countOccurrence(stringResource(Res.string.family_member_homicide))
    val extortionCount = countOccurrence(stringResource(Res.string.extortion))
    val recruitmentCount = countOccurrence(stringResource(Res.string.recruitment))
    val otherCount = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.riskSituationType !in knownRisks && formDateLocal >= startDate && formDateLocal <= endDate
    }

    val totalCount =
        threatCount + attackCount + kidnappingCount + familyHomicideCount + extortionCount + recruitmentCount + otherCount


    fun calculatePercentage(count: Int) =
        if (totalCount > 0) (count.toDouble() / totalCount) * 100 else 0.0

    val threatPercentage = calculatePercentage(threatCount)
    val attackPercentage = calculatePercentage(attackCount)
    val kidnappingPercentage = calculatePercentage(kidnappingCount)
    val familyMemberHomicidePercentage = calculatePercentage(familyHomicideCount)
    val extortionPercentage = calculatePercentage(extortionCount)
    val recruitmentPercentage = calculatePercentage(recruitmentCount)
    val otherPercentage = calculatePercentage(otherCount)


    val threatTypeDataSet = mapOf(
        stringResource(Res.string.threat_means_short) to listOf(
            Triple(stringResource(Res.string.threat), threatPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.attack), attackPercentage, Color(0xFFEA4335)),
            Triple(
                stringResource(Res.string.kidnapping),
                kidnappingPercentage,
                Color(0xFFFBBC05)
            ),
            Triple(
                stringResource(Res.string.family_member_homicide),
                familyMemberHomicidePercentage,
                Color(0xFF395B50)
            ),
            Triple(
                stringResource(Res.string.extortion),
                extortionPercentage,
                Color(0xFF28913B)
            ),
            Triple(
                stringResource(Res.string.recruitment),
                recruitmentPercentage,
                Color(0xFF6EA6FF)
            ),
            Triple(stringResource(Res.string.other), otherPercentage, Color(0xFF2C3D55))
        )
    )
    return threatTypeDataSet
}

@Composable
fun getAddressZoneDataSet(
    state: StatisticsState,
    filterOption: String,
): List<Triple<String, Double, Color>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.addressZone == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }

    val urbanCount = countOccurrence(stringResource(Res.string.urban))
    val ruralCount = countOccurrence(stringResource(Res.string.rural))

    val totalCount = urbanCount + ruralCount

    val urbanPercentage = if (totalCount > 0) (urbanCount.toDouble() / totalCount) * 100 else 0.0
    val ruralPercentage = if (totalCount > 0) (ruralCount.toDouble() / totalCount) * 100 else 0.0

    val zoneAddressDataSet = listOf(
        Triple(stringResource(Res.string.urban), urbanPercentage, Color(0xFF4285F4)),
        Triple(stringResource(Res.string.rural), ruralPercentage, Color(0xFFEA4335))
    )

    return zoneAddressDataSet
}


@Composable
fun getDemographicProfileDataSet(
    state: StatisticsState,
    filterOption: String,
): Map<String, List<Triple<String, Double, Color>>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countSexOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.sex == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }

    val menCount = countSexOccurrence(stringResource(Res.string.men))
    val womenCount = countSexOccurrence(stringResource(Res.string.women))
    val intersexualCount = countSexOccurrence(stringResource(Res.string.intersexual))

    val totalSexCount = menCount + womenCount + intersexualCount

    val menPercentage = if (totalSexCount > 0) (menCount.toDouble() / totalSexCount) * 100 else 0.0
    val womenPercentage =
        if (totalSexCount > 0) (womenCount.toDouble() / totalSexCount) * 100 else 0.0
    val intersexualPercentage =
        if (totalSexCount > 0) (intersexualCount.toDouble() / totalSexCount) * 100 else 0.0


    fun countGenderOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.gender == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }

    val maleCount = countGenderOccurrence(stringResource(Res.string.male))
    val femaleCount = countGenderOccurrence(stringResource(Res.string.female))
    val transgenderCount = countGenderOccurrence(stringResource(Res.string.transgender))

    val totalGenderCount = maleCount + femaleCount + transgenderCount

    val malePercentage =
        if (totalGenderCount > 0) (maleCount.toDouble() / totalGenderCount) * 100 else 0.0
    val femalePercentage =
        if (totalGenderCount > 0) (femaleCount.toDouble() / totalGenderCount) * 100 else 0.0
    val transgenderPercentage =
        if (totalGenderCount > 0) (transgenderCount.toDouble() / totalGenderCount) * 100 else 0.0


    fun countSexualOrientationOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.sexualOrientation == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }


    val heterosexualCount =
        countSexualOrientationOccurrence(stringResource(Res.string.heterosexual))
    val homosexualCount = countSexualOrientationOccurrence(stringResource(Res.string.homosexual))
    val bisexualCount = countSexualOrientationOccurrence(stringResource(Res.string.bisexual))

    val totalSexualOrientationCount = heterosexualCount + homosexualCount + bisexualCount

    val heterosexualPercentage =
        if (totalSexualOrientationCount > 0) (heterosexualCount.toDouble() / totalSexualOrientationCount) * 100 else 0.0
    val homosexualPercentage =
        if (totalSexualOrientationCount > 0) (homosexualCount.toDouble() / totalSexualOrientationCount) * 100 else 0.0
    val bisexualPercentage =
        if (totalSexualOrientationCount > 0) (bisexualCount.toDouble() / totalSexualOrientationCount) * 100 else 0.0


    fun countAgeOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.age == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }


    val childCount = countAgeOccurrence(stringResource(Res.string.boy_and_girl))
    val teenagerCount = countAgeOccurrence(stringResource(Res.string.teenager))
    val youngAdultCount = countAgeOccurrence(stringResource(Res.string.young_adult))
    val adultCount = countAgeOccurrence(stringResource(Res.string.adult))
    val elderlyCount = countAgeOccurrence(stringResource(Res.string.elderly))


    val totalAgeCount = childCount + teenagerCount + youngAdultCount + adultCount + elderlyCount

    val childPercentage =
        if (totalAgeCount > 0) (childCount.toDouble() / totalAgeCount) * 100 else 0.0
    val teenagerPercentage =
        if (totalAgeCount > 0) (teenagerCount.toDouble() / totalAgeCount) * 100 else 0.0
    val youngAdultPercentage =
        if (totalAgeCount > 0) (youngAdultCount.toDouble() / totalAgeCount) * 100 else 0.0
    val adultPercentage =
        if (totalAgeCount > 0) (adultCount.toDouble() / totalAgeCount) * 100 else 0.0
    val elderlyPercentage =
        if (totalAgeCount > 0) (elderlyCount.toDouble() / totalAgeCount) * 100 else 0.0


    val demographicProfileDataSet = mapOf(
        stringResource(Res.string.sexTitle) to listOf(
            Triple(stringResource(Res.string.men), menPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.women), womenPercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.intersexual), intersexualPercentage, Color(0xFFFBBC05))
        ),
        stringResource(Res.string.genderTitle) to listOf(
            Triple(stringResource(Res.string.male), malePercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.female), femalePercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.transgender), transgenderPercentage, Color(0xFFFBBC05))
        ),
        stringResource(Res.string.sexual_orientationTitle) to listOf(
            Triple(
                stringResource(Res.string.heterosexual),
                heterosexualPercentage,
                Color(0xFF4285F4)
            ),
            Triple(stringResource(Res.string.homosexual), homosexualPercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.bisexual), bisexualPercentage, Color(0xFFFBBC05))
        ),
        stringResource(Res.string.ageTitle) to listOf(
            Triple(stringResource(Res.string.boy_and_girl), childPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.teenager), teenagerPercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.young_adult), youngAdultPercentage, Color(0xFFFBBC05)),
            Triple(stringResource(Res.string.adult), adultPercentage, Color(0xFF395B50)),
            Triple(stringResource(Res.string.elderly), elderlyPercentage, Color(0xFF28913B))
        )
    )

    return demographicProfileDataSet

}


@Composable
fun disabilitiesAndIdentifyingTraitFactorDataSet(
    state: StatisticsState,
    filterOption: String,
): Map<String, List<Triple<String, Double, Color>>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countDisabilitiesOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.disabilityType == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }


    val hearingCount = countDisabilitiesOccurrence(stringResource(Res.string.hearing))
    val visualCount = countDisabilitiesOccurrence(stringResource(Res.string.visual))
    val blindnessCount = countDisabilitiesOccurrence(stringResource(Res.string.blindness))
    val mentalCount = countDisabilitiesOccurrence(stringResource(Res.string.mental))
    val intellectualCount = countDisabilitiesOccurrence(stringResource(Res.string.intellectual))
    val littlePeopleCount = countDisabilitiesOccurrence(stringResource(Res.string.little_people))
    val multipleCount = countDisabilitiesOccurrence(stringResource(Res.string.multiple))


    val totalDisabilitiesCount =
        hearingCount + visualCount + blindnessCount + mentalCount + intellectualCount + littlePeopleCount + multipleCount

    val hearingPercentage =
        if (totalDisabilitiesCount > 0) (hearingCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val visualPercentage =
        if (totalDisabilitiesCount > 0) (visualCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val blindnessPercentage =
        if (totalDisabilitiesCount > 0) (blindnessCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val mentalMemberHomicidePercentage =
        if (totalDisabilitiesCount > 0) (mentalCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val intellectualPercentage =
        if (totalDisabilitiesCount > 0) (intellectualCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val littlePeoplePercentage =
        if (totalDisabilitiesCount > 0) (littlePeopleCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0
    val multiplePercentage =
        if (totalDisabilitiesCount > 0) (multipleCount.toDouble() / totalDisabilitiesCount) * 100 else 0.0


    fun countIdentifyingTraitOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.identifyingTrait == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }


    val motherCount = countIdentifyingTraitOccurrence(stringResource(Res.string.mother))
    val fatherCount = countIdentifyingTraitOccurrence(stringResource(Res.string.father))
    val caretakerCount = countIdentifyingTraitOccurrence(stringResource(Res.string.caretaker))
    val peopleUnderCareCount =
        countIdentifyingTraitOccurrence(stringResource(Res.string.people_under_care))


    val totalIdentifyingTraitCount =
        motherCount + fatherCount + caretakerCount + peopleUnderCareCount

    val motherPercentage =
        if (totalIdentifyingTraitCount > 0) (motherCount.toDouble() / totalIdentifyingTraitCount) * 100 else 0.0
    val fatherPercentage =
        if (totalIdentifyingTraitCount > 0) (fatherCount.toDouble() / totalIdentifyingTraitCount) * 100 else 0.0
    val caretakerPercentage =
        if (totalIdentifyingTraitCount > 0) (caretakerCount.toDouble() / totalIdentifyingTraitCount) * 100 else 0.0
    val peopleUnderCarePercentage =
        if (totalIdentifyingTraitCount > 0) (peopleUnderCareCount.toDouble() / totalIdentifyingTraitCount) * 100 else 0.0


    val disabilityAndIdentifyingTraitDataSet = mapOf(
        stringResource(Res.string.disability_type_title_2) to listOf(
            Triple(stringResource(Res.string.hearing), hearingPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.visual), visualPercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.blindness), blindnessPercentage, Color(0xFFFBBC05)),
            Triple(
                stringResource(Res.string.mental),
                mentalMemberHomicidePercentage,
                Color(0xFF395B50)
            ),
            Triple(
                stringResource(Res.string.intellectual),
                intellectualPercentage,
                Color(0xFF28913B)
            ),
            Triple(
                stringResource(Res.string.little_people),
                littlePeoplePercentage,
                Color(0xFF6EA6FF)
            ),
            Triple(stringResource(Res.string.multiple), multiplePercentage, Color(0xFF2C3D55))
        ),
        stringResource(Res.string.different_identifying_trait_title) to listOf(
            Triple(stringResource(Res.string.mother), motherPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.father), fatherPercentage, Color(0xFFEA4335)),
            Triple(stringResource(Res.string.caretaker), caretakerPercentage, Color(0xFFFBBC05)),
            Triple(
                stringResource(Res.string.people_under_care),
                peopleUnderCarePercentage,
                Color(0xFF395B50)
            )
        )
    )
    return disabilityAndIdentifyingTraitDataSet
}

@Composable
fun getOrganizationAffiliationDataSet(
    state: StatisticsState,
    filterOption: String,
): Map<String, List<Triple<String, Double, Color>>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.organizationType == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }

    val knownRisks = setOf(
        stringResource(Res.string.guild),
        stringResource(Res.string.civil),
        stringResource(Res.string.communal),
        stringResource(Res.string.farmer),
        stringResource(Res.string.victims),
        stringResource(Res.string.human_rights_watch)
    )


    val guildCount = countOccurrence(stringResource(Res.string.guild))
    val civilCount = countOccurrence(stringResource(Res.string.civil))
    val communalCount = countOccurrence(stringResource(Res.string.communal))
    val farmerCount = countOccurrence(stringResource(Res.string.farmer))
    val victimsCount = countOccurrence(stringResource(Res.string.victims))
    val humanRightsCount = countOccurrence(stringResource(Res.string.human_rights_watch))
    val otherCount = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.organizationType !in knownRisks && formDateLocal >= startDate && formDateLocal <= endDate
    }

    val totalCount =
        guildCount + civilCount + communalCount + farmerCount + victimsCount + humanRightsCount + otherCount


    fun calculatePercentage(count: Int) =
        if (totalCount > 0) (count.toDouble() / totalCount) * 100 else 0.0

    val guildPercentage = calculatePercentage(guildCount)
    val civilPercentage = calculatePercentage(civilCount)
    val communalPercentage = calculatePercentage(communalCount)
    val farmerPercentage = calculatePercentage(farmerCount)
    val victimsPercentage = calculatePercentage(victimsCount)
    val humanRightsPercentage = calculatePercentage(humanRightsCount)
    val otherPercentage = calculatePercentage(otherCount)


    val threatTypeDataSet = mapOf(
        stringResource(Res.string.organization_membership) to listOf(
            Triple(stringResource(Res.string.guild), guildPercentage, Color(0xFF4285F4)),
            Triple(stringResource(Res.string.civil), civilPercentage, Color(0xFFEA4335)),
            Triple(
                stringResource(Res.string.communal),
                communalPercentage,
                Color(0xFFFBBC05)
            ),
            Triple(
                stringResource(Res.string.farmer),
                farmerPercentage,
                Color(0xFF395B50)
            ),
            Triple(
                stringResource(Res.string.victims),
                victimsPercentage,
                Color(0xFF28913B)
            ),
            Triple(
                stringResource(Res.string.human_rights_watch),
                humanRightsPercentage,
                Color(0xFF6EA6FF)
            ),
            Triple(stringResource(Res.string.other), otherPercentage, Color(0xFF2C3D55))
        )
    )
    return threatTypeDataSet
}

@Composable
fun getProvisionalMeasureDataSet(
    state: StatisticsState,
    filterOption: String,
): List<Triple<String, Double, Color>> {

    val (startDate, endDate) = getDateRange(filterOption)

    fun countOccurrence(type: String) = state.fetchedForms.count {
        val formDate = it.formDate
        val formDateLocal = formDate.toLocalDateFromDdMmYyyy()
        formDateLocal != null && it.applicantData.provisionalMeasuresStatus == type &&
                formDateLocal >= startDate && formDateLocal <= endDate
    }

    val positiveCount = countOccurrence(stringResource(Res.string.yes))
    val negativeCount = countOccurrence(stringResource(Res.string.no))

    val totalCount = positiveCount + negativeCount

    val urbanPercentage = if (totalCount > 0) (positiveCount.toDouble() / totalCount) * 100 else 0.0
    val ruralPercentage = if (totalCount > 0) (negativeCount.toDouble() / totalCount) * 100 else 0.0

    val provisionalMeasureDataSet = listOf(
        Triple(stringResource(Res.string.yes), urbanPercentage, Color(0xFF4285F4)),
        Triple(stringResource(Res.string.no), ruralPercentage, Color(0xFFEA4335))
    )

    return provisionalMeasureDataSet
}
