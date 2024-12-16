package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.sublimetech.lideres.core.design_system.CustomText
import co.sublimetech.lideres.core.design_system.CustomTextField
import co.sublimetech.lideres.core.design_system.DropdownComponent
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_CITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DETAILS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_DISTRICT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_NEIGHBORHOOD
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_SETTLEMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ADDRESS_ZONE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AFRICAN_AMERICAN_COMMUNITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_AGE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_CITY_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_COUNTRY_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DATE_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DEPARTMENT_OF_BIRTH
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_STATUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_DISABILITY_TYPE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_EMAIL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_STATUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ETHNIC_GROUP_TYPE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_LAST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_GENDER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_AMOUNT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_EXPEDITION_DATE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_PARTIAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_LANDLINE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_CITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ORGANIZATION_TYPE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PHONE_NUMBER_1
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PHONE_NUMBER_2
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SECOND_LAST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SECOND_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEX
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SEXUAL_ORIENTATION
import co.sublimetech.lideres.core.presentation.Constants.FORM_CITY
import co.sublimetech.lideres.core.presentation.Constants.FORM_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.FORM_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.FORM_FILL_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.FORM_OFFICE
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_OTHER
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_MEANS_TYPE
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_OTHER
import co.sublimetech.lideres.core.presentation.Constants.RISK_SITUATION_TYPE
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
import lideres.composeapp.generated.resources.address_details
import lideres.composeapp.generated.resources.adult
import lideres.composeapp.generated.resources.african_american
import lideres.composeapp.generated.resources.african_american_community_name
import lideres.composeapp.generated.resources.age
import lideres.composeapp.generated.resources.another
import lideres.composeapp.generated.resources.applicant_personal_data_title
import lideres.composeapp.generated.resources.attack
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
import lideres.composeapp.generated.resources.email
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
import lideres.composeapp.generated.resources.form_date_title
import lideres.composeapp.generated.resources.form_details
import lideres.composeapp.generated.resources.form_number
import lideres.composeapp.generated.resources.gender
import lideres.composeapp.generated.resources.gipsy
import lideres.composeapp.generated.resources.guild
import lideres.composeapp.generated.resources.hearing
import lideres.composeapp.generated.resources.heterosexual
import lideres.composeapp.generated.resources.home_address
import lideres.composeapp.generated.resources.home_location
import lideres.composeapp.generated.resources.home_zone
import lideres.composeapp.generated.resources.homosexual
import lideres.composeapp.generated.resources.how_many
import lideres.composeapp.generated.resources.human_rights_watch
import lideres.composeapp.generated.resources.human_rights_watch_legal_representative
import lideres.composeapp.generated.resources.human_rights_watch_organization_name
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
import lideres.composeapp.generated.resources.nuip
import lideres.composeapp.generated.resources.number
import lideres.composeapp.generated.resources.office
import lideres.composeapp.generated.resources.organization_membership_subtitle
import lideres.composeapp.generated.resources.organization_membership_title
import lideres.composeapp.generated.resources.partial
import lideres.composeapp.generated.resources.people_under_care
import lideres.composeapp.generated.resources.physical
import lideres.composeapp.generated.resources.place_of_birth
import lideres.composeapp.generated.resources.provisional_measures_beneficiary
import lideres.composeapp.generated.resources.provisional_measures_type
import lideres.composeapp.generated.resources.recruitment
import lideres.composeapp.generated.resources.reservation_community
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
import lideres.composeapp.generated.resources.third_party_home_address
import lideres.composeapp.generated.resources.third_party_home_location
import lideres.composeapp.generated.resources.third_party_names_and_lastnames
import lideres.composeapp.generated.resources.third_party_request_title
import lideres.composeapp.generated.resources.threat
import lideres.composeapp.generated.resources.threat_means
import lideres.composeapp.generated.resources.through_third_party
import lideres.composeapp.generated.resources.transgender
import lideres.composeapp.generated.resources.urban
import lideres.composeapp.generated.resources.verbal
import lideres.composeapp.generated.resources.victims
import lideres.composeapp.generated.resources.visual
import lideres.composeapp.generated.resources.which
import lideres.composeapp.generated.resources.women
import lideres.composeapp.generated.resources.written
import lideres.composeapp.generated.resources.yes
import lideres.composeapp.generated.resources.young_adult
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



    LazyColumn(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        item {

            /** Title*/
            CustomText(stringResource(Res.string.applicant_personal_data_title))


            /** Block 1*/
            CustomText(stringResource(Res.string.form_details))

            CustomTextField(
                state.fieldValues[FORM_NUMBER]!!,
                title = stringResource(Res.string.form_number),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[FORM_DATE]!!,
                title = stringResource(Res.string.form_date),
                dateFormat = true,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[FORM_OFFICE]!!,
                title = stringResource(Res.string.office),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            /** Block 2*/
            CustomText(stringResource(Res.string.form_date_title))

            CustomTextField(
                state.fieldValues[FORM_FILL_DATE]!!,
                dateFormat = true,
                title = stringResource(Res.string.date),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[FORM_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[FORM_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[FORM_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            /** Block 3*/
            CustomText(stringResource(Res.string.applicant_personal_data_title))

            CustomTextField(
                state.fieldValues[APPLICANT_FIRST_NAME]!!,
                title = stringResource(Res.string.first_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_SECOND_NAME]!!,
                title = stringResource(Res.string.second_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_FIRST_LAST_NAME]!!,
                title = stringResource(Res.string.first_last_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_SECOND_LAST_NAME]!!,
                title = stringResource(Res.string.second_last_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_IDENTIFYING_NAME]!!,
                title = stringResource(Res.string.identifying_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_FIRST_NAME]!!,
                title = stringResource(Res.string.id_type_and_number),
                options = listOf(
                    stringResource(Res.string.national_id),
                    stringResource(Res.string.foreign_id),
                    stringResource(Res.string.nuip)
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ID_NUMBER]!!,
                title = stringResource(Res.string.number),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ID_EXPEDITION_DATE]!!,
                title = stringResource(Res.string.id_expedition_date),
                dateFormat = true,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomText(
                stringResource(Res.string.place_of_birth),
            )

            CustomTextField(
                state.fieldValues[APPLICANT_COUNTRY_OF_BIRTH]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_DEPARTMENT_OF_BIRTH]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_CITY_OF_BIRTH]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_DATE_OF_BIRTH]!!,
                title = stringResource(Res.string.date_of_birth),
                dateFormat = true,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomText(
                stringResource(Res.string.home_location),
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_DISTRICT]!!,
                title = stringResource(Res.string.district),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_SETTLEMENT]!!,
                title = stringResource(Res.string.settlement),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_NEIGHBORHOOD]!!,
                title = stringResource(Res.string.neighborhood),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_ADDRESS_ZONE]!!,
                title = stringResource(Res.string.home_zone),
                options = listOf(
                    stringResource(Res.string.rural),
                    stringResource(Res.string.urban),
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS]!!,
                title = stringResource(Res.string.home_address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_ADDRESS_DETAILS]!!,
                title = stringResource(Res.string.address_details),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomText(
                stringResource(Res.string.contact_number),
            )

            CustomTextField(
                state.fieldValues[APPLICANT_PHONE_NUMBER_1]!!,
                title = stringResource(Res.string.cellphone_1),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_PHONE_NUMBER_2]!!,
                title = stringResource(Res.string.cellphone_2),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_LANDLINE]!!,
                title = stringResource(Res.string.landline),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_EMAIL]!!,
                title = stringResource(Res.string.email),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            CustomText(
                stringResource(Res.string.notification_address),
            )

            CustomTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_NOTIFICATION_ADDRESS]!!,
                title = stringResource(Res.string.address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            /** Block 4*/
            CustomText(
                stringResource(Res.string.third_party_request_title),
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_NAMES_AND_LASTNAMES]!!,
                title = stringResource(Res.string.third_party_names_and_lastnames),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomText(
                stringResource(Res.string.third_party_home_location),
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_COUNTRY]!!,
                title = stringResource(Res.string.country),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DEPARTMENT]!!,
                title = stringResource(Res.string.department),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_CITY]!!,
                title = stringResource(Res.string.city),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DISTRICT]!!,
                title = stringResource(Res.string.district),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_SETTLEMENT]!!,
                title = stringResource(Res.string.settlement),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_NEIGHBORHOOD]!!,
                title = stringResource(Res.string.neighborhood),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS]!!,
                title = stringResource(Res.string.third_party_home_address),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_ADDRESS_DETAILS]!!,
                title = stringResource(Res.string.address_details),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomText(
                stringResource(Res.string.contact_number),
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_PHONE_NUMBER_1]!!,
                title = stringResource(Res.string.cellphone_1),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_PHONE_NUMBER_2]!!,
                title = stringResource(Res.string.cellphone_2),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_LANDLINE]!!,
                title = stringResource(Res.string.landline),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[THIRD_PARTY_EMAIL]!!,
                title = stringResource(Res.string.email),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            /** Block 5*/
            CustomText(stringResource(Res.string.applicant_personal_data_title))

            DropdownComponent(
                state.fieldValues[APPLICANT_SEX]!!,
                title = stringResource(Res.string.sex),
                options = listOf(
                    stringResource(Res.string.men),
                    stringResource(Res.string.women),
                    stringResource(Res.string.intersexual),
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_GENDER]!!,
                title = stringResource(Res.string.gender),
                options = listOf(
                    stringResource(Res.string.female),
                    stringResource(Res.string.male),
                    stringResource(Res.string.transgender)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_SEXUAL_ORIENTATION]!!,
                title = stringResource(Res.string.sexual_orientation),
                options = listOf(
                    stringResource(Res.string.heterosexual),
                    stringResource(Res.string.homosexual),
                    stringResource(Res.string.bisexual)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_AGE]!!,
                title = stringResource(Res.string.age),
                options = listOf(
                    stringResource(Res.string.boy_and_girl),
                    stringResource(Res.string.teenager),
                    stringResource(Res.string.young_adult),
                    stringResource(Res.string.adult),
                    stringResource(Res.string.elderly)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_IDENTIFYING_TRAIT]!!,
                title = stringResource(Res.string.different_identifying_trait),
                options = listOf(
                    stringResource(Res.string.mother),
                    stringResource(Res.string.father),
                    stringResource(Res.string.caretaker),
                    stringResource(Res.string.people_under_care),
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[APPLICANT_IDENTIFYING_TRAIT_AMOUNT]!!,
                title = stringResource(Res.string.how_many),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_DISABILITY_STATUS]!!,
                title = stringResource(Res.string.disability_type_title),
                options = listOf(
                    stringResource(Res.string.yes),
                    stringResource(Res.string.no)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_DISABILITY_TYPE]!!,
                title = stringResource(Res.string.disability_type_subtitle),
                options = listOf(
                    stringResource(Res.string.physical),
                    stringResource(Res.string.hearing),
                    stringResource(Res.string.visual),
                    stringResource(Res.string.blindness),
                    stringResource(Res.string.mental),
                    stringResource(Res.string.intellectual),
                    stringResource(Res.string.little_people),
                    stringResource(Res.string.multiple)
                ),
                padding = 16.dp
            )


            DropdownComponent(
                state.fieldValues[APPLICANT_ETHNIC_GROUP_STATUS]!!,
                title = stringResource(Res.string.ethnic_group_title),
                options = listOf(
                    stringResource(Res.string.yes),
                    stringResource(Res.string.no)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_ETHNIC_GROUP_TYPE]!!,
                title = stringResource(Res.string.ethnic_group_subtitle),
                options = listOf(
                    stringResource(Res.string.indigenous),
                    stringResource(Res.string.negro),
                    stringResource(Res.string.african_american),
                    stringResource(Res.string.indigenous_to_san_andres),
                    stringResource(Res.string.indigenous_to_bolivar),
                    stringResource(Res.string.gipsy),
                    stringResource(Res.string.little_people),
                    stringResource(Res.string.multiple)
                ),
                padding = 16.dp
            )

            CustomText(
                title = stringResource(Res.string.indigenous_group_title)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP]!!,
                title = stringResource(Res.string.indigenous_group),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_RESERVATION]!!,
                title = stringResource(Res.string.indigenous_reservation),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY]!!,
                title = stringResource(Res.string.reservation_community),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_PARTIAL]!!,
                title = stringResource(Res.string.partial),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )
            CustomTextField(
                state.fieldValues[APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY]!!,
                title = stringResource(Res.string.no_registry_community),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_AFRICAN_AMERICAN_COMMUNITY]!!,
                title = stringResource(Res.string.african_american_community_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            DropdownComponent(
                state.fieldValues[APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS]!!,
                title = stringResource(Res.string.organization_membership_title),
                options = listOf(
                    stringResource(Res.string.yes),
                    stringResource(Res.string.no)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_ORGANIZATION_TYPE]!!,
                title = stringResource(Res.string.organization_membership_subtitle),
                options = listOf(
                    stringResource(Res.string.social),
                    stringResource(Res.string.guild),
                    stringResource(Res.string.civil),
                    stringResource(Res.string.communal),
                    stringResource(Res.string.farmer),
                    stringResource(Res.string.victims),
                    stringResource(Res.string.human_rights_watch),
                    stringResource(Res.string.another)
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_NAME]!!,
                title = stringResource(Res.string.human_rights_watch_organization_name),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS]!!,
                title = stringResource(Res.string.human_rights_watch_legal_representative),
                options = listOf(
                    stringResource(Res.string.yes),
                    stringResource(Res.string.no)
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_NAME]!!,
                title = stringResource(Res.string.human_rights_watch_legal_representative),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_STATUS]!!,
                title = stringResource(Res.string.provisional_measures_beneficiary),
                options = listOf(
                    stringResource(Res.string.yes),
                    stringResource(Res.string.no)
                ),
                padding = 16.dp
            )

            DropdownComponent(
                state.fieldValues[APPLICANT_PROVISIONAL_MEASURES_TYPE]!!,
                title = stringResource(Res.string.provisional_measures_type),
                options = listOf(
                    stringResource(Res.string.measure_by_inter_american_commission),
                    stringResource(Res.string.measure_by_inter_american_court),
                    stringResource(Res.string.measure_by_national_judge)
                ),
                padding = 16.dp
            )

            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black,
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )

            /** Block 7*/
            CustomText(stringResource(Res.string.risk_situation_title))

            DropdownComponent(
                state.fieldValues[RISK_SITUATION_TYPE]!!,
                title = stringResource(Res.string.risk_situation_subtitle),
                options = listOf(
                    stringResource(Res.string.threat),
                    stringResource(Res.string.attack),
                    stringResource(Res.string.kidnapping),
                    stringResource(Res.string.family_member_homicide),
                    stringResource(Res.string.extortion),
                    stringResource(Res.string.recruitment),
                    stringResource(Res.string.another)
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[RISK_SITUATION_OTHER]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
            )


            DropdownComponent(
                state.fieldValues[RISK_SITUATION_MEANS_TYPE]!!,
                title = stringResource(Res.string.threat_means),
                options = listOf(
                    stringResource(Res.string.by_phone),
                    stringResource(Res.string.verbal),
                    stringResource(Res.string.written),
                    stringResource(Res.string.family_member_homicide),
                    stringResource(Res.string.through_third_party),
                    stringResource(Res.string.symbolic),
                    stringResource(Res.string.social_media),
                    stringResource(Res.string.another)
                ),
                padding = 16.dp
            )

            CustomTextField(
                state.fieldValues[RISK_SITUATION_MEANS_OTHER]!!,
                title = stringResource(Res.string.which),
                modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)
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
                        }) {
                        Text("Borrar")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        colors = ButtonColors(
                            containerColor = Color.LightGray,
                            contentColor = Color.Black,
                            disabledContainerColor = Color.LightGray,
                            disabledContentColor = Color.LightGray
                        ),
                        onClick = {
                            action(SignatureAction.COMPLETE)
                        }) {
                        Text("Completar")
                    }
                }
            }

            Button(colors = ButtonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.LightGray
            ), modifier = Modifier.padding(start = 16.dp),
                onClick = {
                onAction(FormAction.OnSaveFormClick)
            }) {


                Text("Guardar Formulario")
            }
            Button(colors = ButtonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.LightGray
            ), modifier = Modifier.padding(start = 16.dp),
                onClick = {
                    onAction(FormAction.OnGetFormClick)
                }) {
                Text("Traer Formualrio")
            }


            Button(colors = ButtonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.LightGray
            ), modifier = Modifier.padding(start = 16.dp),
                onClick = {
                    onAction(FormAction.OnGetFormsClick)
                }) {
                Text("Traer Formularios")
            }
        }
    }
}
