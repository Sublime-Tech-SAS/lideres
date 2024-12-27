package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.sublimetech.lideres.core.design_system.BlockTitle
import co.sublimetech.lideres.core.design_system.CustomOutlineTextField
import co.sublimetech.lideres.core.design_system.CustomTextField
import co.sublimetech.lideres.core.design_system.Disclaimer
import co.sublimetech.lideres.core.design_system.OptionsGrid
import co.sublimetech.lideres.core.design_system.Title
import co.sublimetech.lideres.core.design_system.isValidEmail
import co.sublimetech.lideres.core.design_system.theme.Black
import co.sublimetech.lideres.core.design_system.theme.White
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_CITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DETAILS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DISTRICT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_NEIGHBORHOOD
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_SETTLEMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AFRICAN_AMERICAN_COMMUNITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE_ADULT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE_CHILD
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE_ELDERLY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE_TEENAGER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE_YOUNG_ADULT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_CITY_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_COUNTRY_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DATE_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DEPARTMENT_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_STATUS_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_STATUS_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_BLINDNESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_HEARING
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_INTELLECTUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_LITTLE_PEOPLE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_MENTAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_MULTIPLE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_PHYSICAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE_VISUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_EMAIL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_STATUS_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_STATUS_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_AFRICAN_AMERICAN
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_BOLIVAR
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_GIPSY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_INDIGENOUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_NEGRO
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE_SAN_ANDRES
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_LAST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_GENDER_FEMALE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_GENDER_MALE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_GENDER_TRANSGENDER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_AMOUNT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_CARETAKER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_FATHER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_MOTHER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_PEOPLE_UNDER_CARE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_EXPEDITION_DATE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_FOREIGN_ID
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_NATIONAL_ID
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_NUIP
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_PARTIAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_LANDLINE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_APPROVAL_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_APPROVAL_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_CITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_ID_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_CIVIL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_COMMUNAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_FARMER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_GUILD
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_HUMANS_RIGHT_WATCH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_OTHER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_SOCIAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE_VICTIMS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PHONE_NUMBER_1
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PHONE_NUMBER_2
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS_NEGATIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS_POSITIVE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COMMISSION
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COURT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_NATIONAL_JUDGE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SECOND_LAST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SECOND_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEXUAL_ORIENTATION_BISEXUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEXUAL_ORIENTATION_HETEROSEXUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEXUAL_ORIENTATION_HOMOSEXUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEX_INTERSEXUAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEX_MEN
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEX_WOMEN
import co.sublimetech.lideres.core.presentation.Constants.FORM_CITY
import co.sublimetech.lideres.core.presentation.Constants.FORM_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.FORM_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.FORM_FILL_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.FORM_OFFICE
import co.sublimetech.lideres.core.presentation.Constants.RISK_OR_THREAT_REPORT
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_OTHER_ACTUAL
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_BY_PHONE
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_OTHER
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_SOCIAL_MEDIA
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_SYMBOLIC
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_THROUGH_THIRD_PARTY
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_VERBAL
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE_WRITTEN
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_OTHER_ACTUAL
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_ATTACK
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_EXTORTION
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_FAMILY_MEMBER_HOMICIDE
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_KIDNAPPING
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_OTHER
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_RECRUITMENT
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE_THREAT
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_CITY
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_DETAILS
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_DISTRICT
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_NEIGHBORHOOD
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_ADDRESS_SETTLEMENT
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_EMAIL
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_LANDLINE
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_NAMES_AND_LASTNAMES
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_PHONE_NUMBER_1
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_PHONE_NUMBER_2
import io.github.joelkanyi.sain.Sain
import io.github.joelkanyi.sain.SignatureAction
import io.github.joelkanyi.sain.SignatureState
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.address
import lideres.composeapp.generated.resources.adult
import lideres.composeapp.generated.resources.african_american
import lideres.composeapp.generated.resources.african_american_community_name
import lideres.composeapp.generated.resources.age
import lideres.composeapp.generated.resources.applicant_email
import lideres.composeapp.generated.resources.applicant_personal_data_title
import lideres.composeapp.generated.resources.attack
import lideres.composeapp.generated.resources.birth_date
import lideres.composeapp.generated.resources.bisexual
import lideres.composeapp.generated.resources.blindness
import lideres.composeapp.generated.resources.boy_and_girl
import lideres.composeapp.generated.resources.by_phone
import lideres.composeapp.generated.resources.caretaker
import lideres.composeapp.generated.resources.cellphone_1
import lideres.composeapp.generated.resources.cellphone_2
import lideres.composeapp.generated.resources.city
import lideres.composeapp.generated.resources.civil
import lideres.composeapp.generated.resources.communal
import lideres.composeapp.generated.resources.contact_number
import lideres.composeapp.generated.resources.country
import lideres.composeapp.generated.resources.date
import lideres.composeapp.generated.resources.date_of_birth
import lideres.composeapp.generated.resources.department
import lideres.composeapp.generated.resources.different_identifying_trait
import lideres.composeapp.generated.resources.disability_type_subtitle
import lideres.composeapp.generated.resources.disability_type_title
import lideres.composeapp.generated.resources.district
import lideres.composeapp.generated.resources.elderly
import lideres.composeapp.generated.resources.ethnic_group_subtitle
import lideres.composeapp.generated.resources.ethnic_group_title
import lideres.composeapp.generated.resources.extortion
import lideres.composeapp.generated.resources.family_member_homicide
import lideres.composeapp.generated.resources.farmer
import lideres.composeapp.generated.resources.father
import lideres.composeapp.generated.resources.female
import lideres.composeapp.generated.resources.first_last_name
import lideres.composeapp.generated.resources.first_name
import lideres.composeapp.generated.resources.foreign_id
import lideres.composeapp.generated.resources.form_date
import lideres.composeapp.generated.resources.form_date_subtitle
import lideres.composeapp.generated.resources.form_date_title
import lideres.composeapp.generated.resources.form_details
import lideres.composeapp.generated.resources.form_number
import lideres.composeapp.generated.resources.form_title
import lideres.composeapp.generated.resources.futura_md_bt
import lideres.composeapp.generated.resources.gender
import lideres.composeapp.generated.resources.gipsy
import lideres.composeapp.generated.resources.guild
import lideres.composeapp.generated.resources.hearing
import lideres.composeapp.generated.resources.heterosexual
import lideres.composeapp.generated.resources.home_address
import lideres.composeapp.generated.resources.home_address_details
import lideres.composeapp.generated.resources.home_location
import lideres.composeapp.generated.resources.home_zone
import lideres.composeapp.generated.resources.homosexual
import lideres.composeapp.generated.resources.how_many
import lideres.composeapp.generated.resources.human_rights_watch
import lideres.composeapp.generated.resources.id_expedition_date
import lideres.composeapp.generated.resources.id_type_and_number
import lideres.composeapp.generated.resources.identifying_name
import lideres.composeapp.generated.resources.indigenous
import lideres.composeapp.generated.resources.indigenous_group
import lideres.composeapp.generated.resources.indigenous_group_title
import lideres.composeapp.generated.resources.indigenous_reservation
import lideres.composeapp.generated.resources.indigenous_to_bolivar
import lideres.composeapp.generated.resources.indigenous_to_san_andres
import lideres.composeapp.generated.resources.intellectual
import lideres.composeapp.generated.resources.intersexual
import lideres.composeapp.generated.resources.kidnapping
import lideres.composeapp.generated.resources.landline
import lideres.composeapp.generated.resources.little_people
import lideres.composeapp.generated.resources.male
import lideres.composeapp.generated.resources.measure_by_inter_american_commission
import lideres.composeapp.generated.resources.measure_by_inter_american_court
import lideres.composeapp.generated.resources.measure_by_national_judge
import lideres.composeapp.generated.resources.men
import lideres.composeapp.generated.resources.mental
import lideres.composeapp.generated.resources.mother
import lideres.composeapp.generated.resources.multiple
import lideres.composeapp.generated.resources.national_id
import lideres.composeapp.generated.resources.negro
import lideres.composeapp.generated.resources.neighborhood
import lideres.composeapp.generated.resources.no
import lideres.composeapp.generated.resources.no_registry_community
import lideres.composeapp.generated.resources.notification_address
import lideres.composeapp.generated.resources.notifications_disclaimer
import lideres.composeapp.generated.resources.notifications_request
import lideres.composeapp.generated.resources.nuip
import lideres.composeapp.generated.resources.number
import lideres.composeapp.generated.resources.office
import lideres.composeapp.generated.resources.organization_legal_representative
import lideres.composeapp.generated.resources.organization_legal_representative_id_number
import lideres.composeapp.generated.resources.organization_membership_subtitle
import lideres.composeapp.generated.resources.organization_membership_title
import lideres.composeapp.generated.resources.organization_name
import lideres.composeapp.generated.resources.other
import lideres.composeapp.generated.resources.partial
import lideres.composeapp.generated.resources.people_under_care
import lideres.composeapp.generated.resources.physical
import lideres.composeapp.generated.resources.place_of_birth
import lideres.composeapp.generated.resources.population_object_of_protection_subtitle
import lideres.composeapp.generated.resources.population_object_of_protection_title
import lideres.composeapp.generated.resources.population_of_protection_and_prevention_program
import lideres.composeapp.generated.resources.provisional_measures_beneficiary
import lideres.composeapp.generated.resources.provisional_measures_type
import lideres.composeapp.generated.resources.recruitment
import lideres.composeapp.generated.resources.reservation_community
import lideres.composeapp.generated.resources.risk_or_threat_subtitle
import lideres.composeapp.generated.resources.risk_or_threat_title
import lideres.composeapp.generated.resources.risk_situation_subtitle
import lideres.composeapp.generated.resources.risk_situation_title
import lideres.composeapp.generated.resources.rural
import lideres.composeapp.generated.resources.second_last_name
import lideres.composeapp.generated.resources.second_name
import lideres.composeapp.generated.resources.settlement
import lideres.composeapp.generated.resources.sex
import lideres.composeapp.generated.resources.sexual_orientation
import lideres.composeapp.generated.resources.social
import lideres.composeapp.generated.resources.social_media
import lideres.composeapp.generated.resources.symbolic
import lideres.composeapp.generated.resources.teenager
import lideres.composeapp.generated.resources.third_party_contact_number
import lideres.composeapp.generated.resources.third_party_email
import lideres.composeapp.generated.resources.third_party_home_address
import lideres.composeapp.generated.resources.third_party_home_address_details
import lideres.composeapp.generated.resources.third_party_home_location
import lideres.composeapp.generated.resources.third_party_names_and_lastnames
import lideres.composeapp.generated.resources.third_party_notifications_request
import lideres.composeapp.generated.resources.third_party_request_disclaimer
import lideres.composeapp.generated.resources.third_party_request_title
import lideres.composeapp.generated.resources.threat
import lideres.composeapp.generated.resources.threat_means
import lideres.composeapp.generated.resources.through_third_party
import lideres.composeapp.generated.resources.transgender
import lideres.composeapp.generated.resources.unp_use_disclaimer
import lideres.composeapp.generated.resources.urban
import lideres.composeapp.generated.resources.verbal
import lideres.composeapp.generated.resources.victims
import lideres.composeapp.generated.resources.visual
import lideres.composeapp.generated.resources.which
import lideres.composeapp.generated.resources.women
import lideres.composeapp.generated.resources.written
import lideres.composeapp.generated.resources.yes
import lideres.composeapp.generated.resources.young_adult
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FormScreenRoot(
    viewModel: FormViewModel = koinViewModel(),
    onStatisticsClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()



    FormScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is FormAction.OnStatisticsClick -> onStatisticsClick()
                else -> {}
            }
            viewModel.onAction(action)
        },
    )
}


@Composable
fun FormScreen(
    state: FormState,
    onAction: (FormAction) -> Unit,
) {

    var dateError by remember { mutableStateOf("") }
    var emailPatternError by remember { mutableStateOf("") }


    LazyColumn(
        Modifier
            .background(White)
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            /** Title*/
            Text(
                text = stringResource(Res.string.form_title),
                color = Black,
                fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            /** Block 1*/
            BlockTitle(
                title = stringResource(Res.string.form_details),
                subtitle = stringResource(Res.string.unp_use_disclaimer),
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[FORM_NUMBER]!!,
                title = stringResource(Res.string.form_number),
                onlyDigits = true,
            )

            CustomOutlineTextField(
                state.fieldValues[FORM_DATE]!!,
                title = stringResource(Res.string.form_date),
                dateFormat = true,
            )

            CustomOutlineTextField(
                state.fieldValues[FORM_OFFICE]!!,
                title = stringResource(Res.string.office),
                maxLength = 100,
            )

            /** Block 2*/
            BlockTitle(
                title = stringResource(Res.string.form_date_title),
                subtitle = stringResource(Res.string.form_date_subtitle),
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )


            CustomOutlineTextField(
                state.fieldValues[FORM_FILL_DATE]!!,
                dateFormat = true,
                title = stringResource(Res.string.date),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[FORM_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[FORM_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[FORM_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            /** Block 3*/
            BlockTitle(
                title = stringResource(Res.string.applicant_personal_data_title),
                subtitle = null,
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_FIRST_NAME]!!,
                title = stringResource(Res.string.first_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_SECOND_NAME]!!,
                title = stringResource(Res.string.second_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_FIRST_LAST_NAME]!!,
                title = stringResource(Res.string.first_last_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_SECOND_LAST_NAME]!!,
                title = stringResource(Res.string.second_last_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_IDENTIFYING_NAME]!!,
                title = stringResource(Res.string.identifying_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.id_type_and_number), 12,
            )

            val applicantIdOptions = listOf(
                stringResource(Res.string.national_id) to state.fieldValues[APPLICANT_ID_NATIONAL_ID]!!,
                stringResource(Res.string.foreign_id) to state.fieldValues[APPLICANT_ID_FOREIGN_ID]!!,
                stringResource(Res.string.nuip) to state.fieldValues[APPLICANT_ID_NUIP]!!
            )


            OptionsGrid(
                options = applicantIdOptions,
                columns = 2,
                onOptionSelected = { selectedIndex ->
                    applicantIdOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) applicantIdOptions[index].first else ""
                            )
                        }
                    }
                }
            ) //    if cedula de ciudadania abajo only numbers


            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ID_NUMBER]!!,
                title = stringResource(Res.string.number),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ID_EXPEDITION_DATE]!!,
                title = stringResource(Res.string.id_expedition_date),
                dateFormat = true,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.place_of_birth),
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_COUNTRY_OF_BIRTH]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_DEPARTMENT_OF_BIRTH]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_CITY_OF_BIRTH]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.birth_date),
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_DATE_OF_BIRTH]!!,
                title = stringResource(Res.string.date_of_birth),
                dateFormat = true,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.home_location),
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_DISTRICT]!!,
                title = stringResource(Res.string.district),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_SETTLEMENT]!!,
                title = stringResource(Res.string.settlement),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_NEIGHBORHOOD]!!,
                title = stringResource(Res.string.neighborhood),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.home_zone), 12,
            )

            val homeZoneOptions = listOf(
                stringResource(Res.string.rural) to state.fieldValues[APPLICANT_ID_NATIONAL_ID]!!,
                stringResource(Res.string.urban) to state.fieldValues[APPLICANT_ID_FOREIGN_ID]!!,
            )


            OptionsGrid(
                options = homeZoneOptions,
                columns = 2,
                onOptionSelected = { selectedIndex ->
                    homeZoneOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) homeZoneOptions[index].first else ""
                            )
                        }
                    }
                }
            )


            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS]!!,
                title = stringResource(Res.string.home_address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ADDRESS_DETAILS]!!,
                title = stringResource(Res.string.home_address_details),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.contact_number),
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_PHONE_NUMBER_1]!!,
                title = stringResource(Res.string.cellphone_1),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_PHONE_NUMBER_2]!!,
                title = stringResource(Res.string.cellphone_2),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_LANDLINE]!!,
                title = stringResource(Res.string.landline),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_EMAIL]!!,
                title = stringResource(Res.string.applicant_email),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.notifications_request), 12,
            )


            val emailNotificationRequestOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_NOTIFICATION_APPROVAL_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_NOTIFICATION_APPROVAL_NEGATIVE]!!,
            )


            OptionsGrid(
                options = emailNotificationRequestOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    emailNotificationRequestOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) emailNotificationRequestOptions[index].first else ""
                            )
                        }
                    }
                }
            )
            Disclaimer(stringResource(Res.string.notifications_disclaimer))

            Title(
                stringResource(Res.string.notification_address),
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_ADDRESS]!!,
                title = stringResource(Res.string.address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            /** Block 4*/
            BlockTitle(
                stringResource(Res.string.third_party_request_title),
                stringResource(Res.string.third_party_request_disclaimer),
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_NAMES_AND_LASTNAMES]!!,
                title = stringResource(Res.string.third_party_names_and_lastnames),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.third_party_home_location),
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DISTRICT]!!,
                title = stringResource(Res.string.district),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_SETTLEMENT]!!,
                title = stringResource(Res.string.settlement),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_NEIGHBORHOOD]!!,
                title = stringResource(Res.string.neighborhood),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS]!!,
                title = stringResource(Res.string.third_party_home_address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DETAILS]!!,
                title = stringResource(Res.string.third_party_home_address_details),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.third_party_contact_number),
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_PHONE_NUMBER_1]!!,
                title = stringResource(Res.string.cellphone_1),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_PHONE_NUMBER_2]!!,
                title = stringResource(Res.string.cellphone_2),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_LANDLINE]!!,
                title = stringResource(Res.string.landline),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomOutlineTextField(
                state.fieldValues[THIRD_PARTY_EMAIL]!!,
                title = stringResource(Res.string.third_party_email),
                error = emailPatternError,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.third_party_notifications_request), 12
            )

            val thirdEmailNotificationRequestOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_ID_NATIONAL_ID]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_ID_FOREIGN_ID]!!,
            )


            OptionsGrid(
                options = thirdEmailNotificationRequestOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    thirdEmailNotificationRequestOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) thirdEmailNotificationRequestOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Disclaimer(stringResource(Res.string.notifications_disclaimer))


            /** Block 5*/
            BlockTitle(
                stringResource(Res.string.applicant_personal_data_title),
                null,
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )

            Title(
                stringResource(Res.string.sex), 12,
            )

            val sexOptions = listOf(
                stringResource(Res.string.men) to state.fieldValues[APPLICANT_SEX_MEN]!!,
                stringResource(Res.string.women) to state.fieldValues[APPLICANT_SEX_WOMEN]!!,
                stringResource(Res.string.intersexual) to state.fieldValues[APPLICANT_SEX_INTERSEXUAL]!!,
            )


            OptionsGrid(
                options = sexOptions,
                columns = 3,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    sexOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) sexOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.gender), 12,
            )

            val genderOptions = listOf(
                stringResource(Res.string.male) to state.fieldValues[APPLICANT_GENDER_MALE]!!,
                stringResource(Res.string.female) to state.fieldValues[APPLICANT_GENDER_FEMALE]!!,
                stringResource(Res.string.transgender) to state.fieldValues[APPLICANT_GENDER_TRANSGENDER]!!,
            )


            OptionsGrid(
                options = genderOptions,
                columns = 3,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    genderOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) genderOptions[index].first else ""
                            )
                        }
                    }
                }
            )


            Title(
                stringResource(Res.string.sexual_orientation), 12,
            )

            val sexualOrientationOptions = listOf(
                stringResource(Res.string.heterosexual) to state.fieldValues[APPLICANT_SEXUAL_ORIENTATION_HETEROSEXUAL]!!,
                stringResource(Res.string.homosexual) to state.fieldValues[APPLICANT_SEXUAL_ORIENTATION_HOMOSEXUAL]!!,
                stringResource(Res.string.bisexual) to state.fieldValues[APPLICANT_SEXUAL_ORIENTATION_BISEXUAL]!!,
            )


            OptionsGrid(
                options = sexualOrientationOptions,
                columns = 3,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    sexualOrientationOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) sexualOrientationOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.age), 12,
            )

            val ageOptions = listOf(
                stringResource(Res.string.boy_and_girl) to state.fieldValues[APPLICANT_AGE_CHILD]!!,
                stringResource(Res.string.teenager) to state.fieldValues[APPLICANT_AGE_TEENAGER]!!,
                stringResource(Res.string.young_adult) to state.fieldValues[APPLICANT_AGE_YOUNG_ADULT]!!,
                stringResource(Res.string.adult) to state.fieldValues[APPLICANT_AGE_ADULT]!!,
                stringResource(Res.string.elderly) to state.fieldValues[APPLICANT_AGE_ELDERLY]!!
            )


            OptionsGrid(
                options = ageOptions,
                columns = 1,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    ageOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) ageOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.different_identifying_trait), 12,
            )

            val identifyingTradeOptions = listOf(
                stringResource(Res.string.father) to state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_FATHER]!!,
                stringResource(Res.string.mother) to state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_MOTHER]!!,
                stringResource(Res.string.caretaker) to state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_CARETAKER]!!,
                stringResource(Res.string.people_under_care) to state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_PEOPLE_UNDER_CARE]!!,
            )

            OptionsGrid(
                options = identifyingTradeOptions,
                columns = 1,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    identifyingTradeOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) identifyingTradeOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_AMOUNT]!!,
                title = stringResource(Res.string.how_many),
                error = emailPatternError,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                stringResource(Res.string.disability_type_title), 12,
            )

            val disabilityStatusOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_DISABILITY_STATUS_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_DISABILITY_STATUS_NEGATIVE]!!,
            )


            OptionsGrid(
                options = disabilityStatusOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    disabilityStatusOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) disabilityStatusOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.disability_type_subtitle), 12,
            )


            val disabilityOptions = listOf(
                stringResource(Res.string.physical) to state.fieldValues[APPLICANT_DISABILITY_TYPE_PHYSICAL]!!,
                stringResource(Res.string.hearing) to state.fieldValues[APPLICANT_DISABILITY_TYPE_HEARING]!!,
                stringResource(Res.string.visual) to state.fieldValues[APPLICANT_DISABILITY_TYPE_VISUAL]!!,
                stringResource(Res.string.blindness) to state.fieldValues[APPLICANT_DISABILITY_TYPE_BLINDNESS]!!,
                stringResource(Res.string.mental) to state.fieldValues[APPLICANT_DISABILITY_TYPE_MENTAL]!!,
                stringResource(Res.string.intellectual) to state.fieldValues[APPLICANT_DISABILITY_TYPE_INTELLECTUAL]!!,
                stringResource(Res.string.little_people) to state.fieldValues[APPLICANT_DISABILITY_TYPE_LITTLE_PEOPLE]!!,
                stringResource(Res.string.multiple) to state.fieldValues[APPLICANT_DISABILITY_TYPE_MULTIPLE]!!,
            )

            OptionsGrid(
                options = disabilityOptions,
                columns = 2,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    disabilityOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) disabilityOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.ethnic_group_title), 12,
            )

            val ethnicGroupStatusOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_ETHNIC_GROUP_STATUS_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_ETHNIC_GROUP_STATUS_NEGATIVE]!!,
            )


            OptionsGrid(
                options = ethnicGroupStatusOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    ethnicGroupStatusOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) ethnicGroupStatusOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                stringResource(Res.string.ethnic_group_subtitle), 12,
            )


            val ethnicGroupTypeOptions = listOf(
                stringResource(Res.string.indigenous) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_INDIGENOUS]!!,
                stringResource(Res.string.negro) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_NEGRO]!!,
                stringResource(Res.string.african_american) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_AFRICAN_AMERICAN]!!,
                stringResource(Res.string.indigenous_to_san_andres) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_SAN_ANDRES]!!,
                stringResource(Res.string.indigenous_to_bolivar) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_BOLIVAR]!!,
                stringResource(Res.string.gipsy) to state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE_GIPSY]!!,

                )

            OptionsGrid(
                options = ethnicGroupTypeOptions,
                columns = 2,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    ethnicGroupTypeOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) ethnicGroupTypeOptions[index].first else ""
                            )
                        }
                    }
                }
            )


            Title(
                title = stringResource(Res.string.indigenous_group_title)
            )

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP]!!,
                title = stringResource(Res.string.indigenous_group),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_RESERVATION]!!,
                title = stringResource(Res.string.indigenous_reservation),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY]!!,
                title = stringResource(Res.string.reservation_community),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_PARTIAL]!!,
                title = stringResource(Res.string.partial),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            Disclaimer(stringResource(Res.string.no_registry_community), 0)
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY]!!,
                title = "",
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Disclaimer(stringResource(Res.string.african_american_community_name), 0)
            CustomOutlineTextField(
                state.fieldValues[APPLICANT_AFRICAN_AMERICAN_COMMUNITY]!!,
                title = "",
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            Title(
                title = stringResource(Res.string.organization_membership_title)
            )

            val organizationStatusOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_NEGATIVE]!!,
            )


            OptionsGrid(
                options = organizationStatusOptions,
                columns = 3,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    organizationStatusOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) organizationStatusOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                title = stringResource(Res.string.organization_membership_subtitle)
            )

            val organizationOptions = listOf(
                stringResource(Res.string.social) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_SOCIAL]!!,
                stringResource(Res.string.guild) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_GUILD]!!,
                stringResource(Res.string.civil) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_CIVIL]!!,
                stringResource(Res.string.communal) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_COMMUNAL]!!,
                stringResource(Res.string.farmer) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_FARMER]!!,
                stringResource(Res.string.victims) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_VICTIMS]!!,
                stringResource(Res.string.human_rights_watch) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_HUMANS_RIGHT_WATCH]!!,
                stringResource(Res.string.other) to state.fieldValues[APPLICANT_ORGANIZATION_TYPE_OTHER]!!,
            )

            OptionsGrid(
                options = organizationOptions,
                columns = 2,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    organizationOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) organizationOptions[index].first else ""
                            )
                        }
                    }
                }
            )


            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Disclaimer(stringResource(Res.string.organization_name), 0)

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_NAME]!!,
                title = "",
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                title = stringResource(Res.string.organization_legal_representative)
            )

            val legalRepresentativeOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_NEGATIVE]!!,
            )


            OptionsGrid(
                options = legalRepresentativeOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    legalRepresentativeOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) legalRepresentativeOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Disclaimer(stringResource(Res.string.organization_legal_representative_id_number), 0)

            CustomOutlineTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_ID_NUMBER]!!,
                title = "",
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            Title(
                title = stringResource(Res.string.provisional_measures_beneficiary)
            )

            val provisionalMeasuresStatusOptions = listOf(
                stringResource(Res.string.yes) to state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_STATUS_POSITIVE]!!,
                stringResource(Res.string.no) to state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_STATUS_NEGATIVE]!!,
            )


            OptionsGrid(
                options = provisionalMeasuresStatusOptions,
                columns = 2,
                bottomPadding = 10,
                onOptionSelected = { selectedIndex ->
                    provisionalMeasuresStatusOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) provisionalMeasuresStatusOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            Title(
                title = stringResource(Res.string.provisional_measures_type)
            )

            val provisionalMeasuresTypeOptions = listOf(
                stringResource(Res.string.measure_by_inter_american_commission) to state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COMMISSION]!!,
                stringResource(Res.string.measure_by_inter_american_court) to state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COURT]!!,
                stringResource(Res.string.measure_by_national_judge) to state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_TYPE_NATIONAL_JUDGE]!!,
            )

            OptionsGrid(
                options = provisionalMeasuresTypeOptions,
                columns = 1,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    provisionalMeasuresTypeOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) provisionalMeasuresTypeOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            /** Block 6*/
            BlockTitle(
                stringResource(Res.string.risk_or_threat_title),
                null,
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )

            Disclaimer(stringResource(Res.string.risk_or_threat_subtitle), 0)
            CustomTextField(state.fieldValues[RISK_OR_THREAT_REPORT]!!)


            /** Block 7*/
            BlockTitle(
                stringResource(Res.string.risk_situation_title),
                null,
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
                onClick = {}
            )

            Title(
                title = stringResource(Res.string.risk_situation_subtitle)
            )

            val riskSituationOptions = listOf(
                stringResource(Res.string.threat) to state.fieldValues[RISK_SITUATION_TYPE_THREAT]!!,
                stringResource(Res.string.attack) to state.fieldValues[RISK_SITUATION_TYPE_ATTACK]!!,
                stringResource(Res.string.kidnapping) to state.fieldValues[RISK_SITUATION_TYPE_KIDNAPPING]!!,
                stringResource(Res.string.family_member_homicide) to state.fieldValues[RISK_SITUATION_TYPE_FAMILY_MEMBER_HOMICIDE]!!,
                stringResource(Res.string.extortion) to state.fieldValues[RISK_SITUATION_TYPE_EXTORTION]!!,
                stringResource(Res.string.recruitment) to state.fieldValues[RISK_SITUATION_TYPE_RECRUITMENT]!!,
                stringResource(Res.string.other) to state.fieldValues[RISK_SITUATION_TYPE_OTHER]!!,
            )

            OptionsGrid(
                options = riskSituationOptions,
                columns = 1,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    riskSituationOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) riskSituationOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            CustomOutlineTextField(
                state.fieldValues[RISK_SITUATION_OTHER_ACTUAL]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            Title(
                title = stringResource(Res.string.threat_means)
            )

            val riskSituationMeansOptions = listOf(
                stringResource(Res.string.by_phone) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_BY_PHONE]!!,
                stringResource(Res.string.verbal) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_VERBAL]!!,
                stringResource(Res.string.written) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_WRITTEN]!!,
                stringResource(Res.string.through_third_party) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_THROUGH_THIRD_PARTY]!!,
                stringResource(Res.string.symbolic) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_SYMBOLIC]!!,
                stringResource(Res.string.social_media) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_SOCIAL_MEDIA]!!,
                stringResource(Res.string.other) to state.fieldValues[RISK_SITUATION_MEANS_TYPE_OTHER]!!,
            )

            OptionsGrid(
                options = riskSituationMeansOptions,
                columns = 2,
                bottomPadding = 20,
                onOptionSelected = { selectedIndex ->
                    riskSituationMeansOptions.forEachIndexed { index, pair ->
                        pair.second.edit {
                            replace(
                                0,
                                length,
                                if (index == selectedIndex) riskSituationMeansOptions[index].first else ""
                            )
                        }
                    }
                }
            )

            CustomOutlineTextField(
                state.fieldValues[RISK_SITUATION_MEANS_OTHER_ACTUAL]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            BlockTitle(
                stringResource(Res.string.population_object_of_protection_title),
                stringResource(Res.string.population_object_of_protection_subtitle),
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp),
            )

            BlockTitle(
                title = null,
                subtitle = stringResource(Res.string.population_of_protection_and_prevention_program),
                modifier = Modifier.padding(top = 10.dp, bottom = 24.dp).clickable {},
            )


            var imageBitmap: ImageBitmap? by remember { mutableStateOf(null) }

            Spacer(modifier = Modifier.height(16.dp))


            Sain(
                state = SignatureState(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(horizontal = 16.dp)
                    .border(
                        BorderStroke(
                            width = .5.dp,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                onComplete = { signatureBitmap ->
                    if (signatureBitmap != null) {
                        imageBitmap = signatureBitmap
                    } else {
                        println("Signature is empty")
                    }
                },
            ) { action ->
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.LightGray
                        ),
                        onClick = {
                            imageBitmap = null
                            action(SignatureAction.CLEAR)
                        }) { Text("Borrar") }
                    Button(modifier = Modifier.weight(1f),
                        colors = ButtonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.LightGray
                        ),
                        onClick = {
                            action(SignatureAction.COMPLETE)
                        }) { Text("Completar") }
                }
            }
            Button(
                colors = ButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.LightGray
                ), modifier = Modifier.padding(start = 16.dp), onClick = {

                    onAction(FormAction.OnSaveFormClick)
                }) { Text("Guardar Formulario") }


            LaunchedEffect(state.fieldValues[THIRD_PARTY_EMAIL]!!.text.toString()) {
                if (isValidEmail(state.fieldValues[THIRD_PARTY_EMAIL]!!.text.toString())) {
                    emailPatternError = ""
                } else {
                    emailPatternError = "Invalid email format"
                }
            }
        }
    }
}


//Button(colors = ButtonColors(
//    containerColor = Color.LightGray,
//    contentColor = Color.Black,
//    disabledContainerColor = Color.LightGray,
//    disabledContentColor = Color.LightGray
//), modifier = Modifier.padding(start = 16.dp),
//    onClick = {
//        onAction(FormAction.OnGetFormClick)
//    }) {
//    Text("Traer Formualrio")
//} } }

