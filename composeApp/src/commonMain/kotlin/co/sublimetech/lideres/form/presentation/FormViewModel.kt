package co.sublimetech.lideres.form.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.sublimetech.lideres.core.domain.Result
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
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FINGERPRINT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_LAST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FIRST_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_FULL_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_GENDER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_NAME
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IDENTIFYING_TRAIT_AMOUNT
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_EXPEDITION_DATE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_ID_TYPE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_PARTIAL
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_IS_FORM_ENROLLER
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_LANDLINE
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_ADDRESS
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_NOTIFICATION_APPROVAL
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
import co.sublimetech.lideres.core.presentation.Constants.APPLICANT_SIGNATURE
import co.sublimetech.lideres.core.presentation.Constants.CERREM_WOMEN_ACCEPTANCE
import co.sublimetech.lideres.core.presentation.Constants.DATA_CONSENT_ACCEPTANCE
import co.sublimetech.lideres.core.presentation.Constants.ENROLLER_ENTITY_NAME
import co.sublimetech.lideres.core.presentation.Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP
import co.sublimetech.lideres.core.presentation.Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE
import co.sublimetech.lideres.core.presentation.Constants.FILED_IN_IDENTIFIER
import co.sublimetech.lideres.core.presentation.Constants.FORM_CITY
import co.sublimetech.lideres.core.presentation.Constants.FORM_COUNTRY
import co.sublimetech.lideres.core.presentation.Constants.FORM_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_DEPARTMENT
import co.sublimetech.lideres.core.presentation.Constants.FORM_ENROLLER_EMAIL
import co.sublimetech.lideres.core.presentation.Constants.FORM_ENROLLER_NAME_AND_LASTNAME
import co.sublimetech.lideres.core.presentation.Constants.FORM_ENROLLER_PHONE_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.FORM_FILL_DATE
import co.sublimetech.lideres.core.presentation.Constants.FORM_NUMBER
import co.sublimetech.lideres.core.presentation.Constants.FORM_OFFICE
import co.sublimetech.lideres.core.presentation.Constants.NATIONAL_POLICE_PREVENTIVE_MEASURES_ACCEPTANCE
import co.sublimetech.lideres.core.presentation.Constants.PATRIOTIC_UNION_AND_COMMUNIST_PARTY_SURVIVOR
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_1
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_10
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_11
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_12
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_13
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_14
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_15
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_16
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_17
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_2
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_3
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_4
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_5
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_6
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_7
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_8
import co.sublimetech.lideres.core.presentation.Constants.PROTECTION_GROUP_9
import co.sublimetech.lideres.core.presentation.Constants.PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_EMAIL
import co.sublimetech.lideres.core.presentation.Constants.PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_NAME
import co.sublimetech.lideres.core.presentation.Constants.RISK_OR_THREAT_REPORT
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
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_NOTIFICATION_APPROVAL
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_PHONE_NUMBER_1
import co.sublimetech.lideres.core.presentation.Constants.THIRD_PARTY_PHONE_NUMBER_2
import co.sublimetech.lideres.form.domain.FormRepositoryInterface
import co.sublimetech.lideres.form.domain.toForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get


class FormViewModel : ViewModel(), KoinComponent {

    private val repository: FormRepositoryInterface = get()

    private val _state = MutableStateFlow(FormState())
    val state: StateFlow<FormState> get() = _state

    init {
        val fieldKeys = listOf(
            // BLOCK 1 FORM DETAILS
            FORM_NUMBER,
            FORM_DATE,
            FORM_OFFICE,
            // BLOCK 2 FORM DATE
            FORM_FILL_DATE,
            FORM_COUNTRY,
            FORM_DEPARTMENT,
            FORM_CITY,
            // BLOCK 3 APPLICANT PERSONAL DATA
            APPLICANT_FIRST_NAME,
            APPLICANT_SECOND_NAME,
            APPLICANT_FIRST_LAST_NAME,
            APPLICANT_SECOND_LAST_NAME,
            APPLICANT_IDENTIFYING_NAME,
            APPLICANT_ID_TYPE,
            APPLICANT_ID_NUMBER,
            APPLICANT_ID_EXPEDITION_DATE,
            APPLICANT_COUNTRY_OF_BIRTH,
            APPLICANT_DEPARTMENT_OF_BIRTH,
            APPLICANT_CITY_OF_BIRTH,
            APPLICANT_DATE_OF_BIRTH,
            APPLICANT_ADDRESS_COUNTRY,
            APPLICANT_ADDRESS_DEPARTMENT,
            APPLICANT_ADDRESS_CITY,
            APPLICANT_ADDRESS_DISTRICT,
            APPLICANT_ADDRESS_SETTLEMENT,
            APPLICANT_ADDRESS_NEIGHBORHOOD,
            APPLICANT_ADDRESS_ZONE,
            APPLICANT_ADDRESS,
            APPLICANT_ADDRESS_DETAILS,
            APPLICANT_PHONE_NUMBER_1,
            APPLICANT_PHONE_NUMBER_2,
            APPLICANT_LANDLINE,
            APPLICANT_EMAIL,
            APPLICANT_NOTIFICATION_APPROVAL,
            APPLICANT_NOTIFICATION_COUNTRY,
            APPLICANT_NOTIFICATION_DEPARTMENT,
            APPLICANT_NOTIFICATION_CITY,
            APPLICANT_NOTIFICATION_ADDRESS,
            // BLOCK 4 THIRD PARTY REQUEST DATA
            THIRD_PARTY_NAMES_AND_LASTNAMES,
            THIRD_PARTY_ADDRESS_COUNTRY,
            THIRD_PARTY_ADDRESS_DEPARTMENT,
            THIRD_PARTY_ADDRESS_CITY,
            THIRD_PARTY_ADDRESS_DISTRICT,
            THIRD_PARTY_ADDRESS_SETTLEMENT,
            THIRD_PARTY_ADDRESS_NEIGHBORHOOD,
            THIRD_PARTY_ADDRESS,
            THIRD_PARTY_ADDRESS_DETAILS,
            THIRD_PARTY_PHONE_NUMBER_1,
            THIRD_PARTY_PHONE_NUMBER_2,
            THIRD_PARTY_LANDLINE,
            THIRD_PARTY_EMAIL,
            THIRD_PARTY_NOTIFICATION_APPROVAL,
            // BLOCK 5 APPLICANT PERSONAL DATA 2
            APPLICANT_SEX,
            APPLICANT_GENDER,
            APPLICANT_SEXUAL_ORIENTATION,
            APPLICANT_AGE,
            APPLICANT_IDENTIFYING_TRAIT,
            APPLICANT_IDENTIFYING_TRAIT_AMOUNT,
            APPLICANT_DISABILITY_STATUS,
            APPLICANT_DISABILITY_TYPE,
            APPLICANT_ETHNIC_GROUP_STATUS,
            APPLICANT_ETHNIC_GROUP_TYPE,
            APPLICANT_INDIGENOUS_GROUP,
            APPLICANT_INDIGENOUS_GROUP_RESERVATION,
            APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY,
            APPLICANT_INDIGENOUS_GROUP_PARTIAL,
            APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY,
            APPLICANT_AFRICAN_AMERICAN_COMMUNITY,
            APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS,
            APPLICANT_ORGANIZATION_TYPE,
            APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER,
            APPLICANT_ORGANIZATION_NAME,
            APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS,
            APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_NAME,
            APPLICANT_PROVISIONAL_MEASURES_STATUS,
            APPLICANT_PROVISIONAL_MEASURES_TYPE,
            // BLOCK 6 RISK OR THREAT DATA
            RISK_OR_THREAT_REPORT,
            // BLOCK 7 RISK SITUATION DATA
            RISK_SITUATION_TYPE,
            RISK_SITUATION_OTHER,
            RISK_SITUATION_MEANS_TYPE,
            RISK_SITUATION_MEANS_OTHER,
            // BLOCK 8 POPULATION OBJECT OF PROTECTION
            PROTECTION_GROUP_1,
            PROTECTION_GROUP_2,
            PROTECTION_GROUP_3,
            PROTECTION_GROUP_4,
            PROTECTION_GROUP_5,
            PROTECTION_GROUP_6,
            PROTECTION_GROUP_7,
            PROTECTION_GROUP_8,
            PROTECTION_GROUP_9,
            PROTECTION_GROUP_10,
            PROTECTION_GROUP_11,
            PROTECTION_GROUP_12,
            PROTECTION_GROUP_13,
            PROTECTION_GROUP_14,
            PROTECTION_GROUP_15,
            PROTECTION_GROUP_16,
            PROTECTION_GROUP_17,
            //BLOCK 9 PATRIOTIC UNION AND COMMUNIST PARTY SURVIVOR
            PATRIOTIC_UNION_AND_COMMUNIST_PARTY_SURVIVOR,
            //BLOCK 10 ESPECIAL SECURITY AND PROTECTION GROUP
            ESPECIAL_SECURITY_AND_PROTECTION_GROUP,
            ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE,
            //BLOCK 13 CONSENT
            DATA_CONSENT_ACCEPTANCE,
            NATIONAL_POLICE_PREVENTIVE_MEASURES_ACCEPTANCE,
            CERREM_WOMEN_ACCEPTANCE,
            APPLICANT_FULL_NAME,
            APPLICANT_SIGNATURE,
            APPLICANT_FINGERPRINT,
            //BLOCK 14 ENROLLER INFORMATION
            APPLICANT_IS_FORM_ENROLLER,
            FORM_ENROLLER_NAME_AND_LASTNAME,
            ENROLLER_ENTITY_NAME,
            FORM_ENROLLER_PHONE_NUMBER,
            FORM_ENROLLER_EMAIL,
            //BLOCK 15 PUBLIC SERVANT OR UNP FORM RECEIVER INFORMATION
            PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_NAME,
            PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_EMAIL,
            FILED_IN_IDENTIFIER
        )
        val initialFields = fieldKeys.associateWith { TextFieldState() }
        _state.value = FormState(fieldValues = initialFields)
    }

    fun onAction(action: FormAction) {
        when (action) {
            FormAction.OnSaveFormClick -> {
                saveForm()
            }

            FormAction.OnGetFormClick -> {
                getForm()
            }

            FormAction.OnGetFormsClick -> {
                getForms()
            }

            FormAction.OnStatisticsClick -> {
                //travel to statistics
            }
        }
    }

    private fun saveForm() {
        viewModelScope.launch {
            val form = _state.value.toForm()
            val result = repository.saveForm(form)
            when (result) {
                is Result.Error -> {
                    //HANDLE ERRORS, LOG TO SYSTEM, WARN ETC
                }

                is Result.Success -> {
                    _state.value = _state.value.copy()
                    getForms()
                    //LET USER KNOW?
                }
            }
        }
    }

    private fun getForm() {
        viewModelScope.launch {
            val result = repository.getForm(state.value.fieldValues[FORM_NUMBER]!!.text.toString())
            when (result) {
                is Result.Error -> {
                    //HANDLE ERRORS, LOG TO SYSTEM, WARN ETC
                }

                is Result.Success -> {
                    _state.value =
                        _state.value.copy(fetchedFormNumber = result.data.applicantData.firstName)
                    //LET USER KNOW?
                }
            }
        }
    }


    private fun getForms() {
        viewModelScope.launch {
            repository.getForms()
                .catch { e ->
                    e.printStackTrace()
                    _state.value = _state.value.copy(fetchedForms = emptyList())

                }
                .collect { forms ->
                    _state.value = _state.value.copy(fetchedForms = forms)
                }
        }
    }
}

