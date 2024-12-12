package co.sublimetech.lideres.form.domain

import co.sublimetech.lideres.core.presentation.Constants
import co.sublimetech.lideres.form.presentation.FormState

fun FormState.toForm(): Form {
    val applicant = Applicant(
        firstName = fieldValues[Constants.APPLICANT_FIRST_NAME]!!.text.toString(),
        secondName = fieldValues[Constants.APPLICANT_SECOND_NAME]!!.text.toString(),
        firstLastName = fieldValues[Constants.APPLICANT_FIRST_LAST_NAME]!!.text.toString(),
        secondLastName = fieldValues[Constants.APPLICANT_SECOND_LAST_NAME]!!.text.toString(),
        identifyingName = fieldValues[Constants.APPLICANT_IDENTIFYING_NAME]!!.text.toString(),
        idType = fieldValues[Constants.APPLICANT_ID_TYPE]!!.text.toString(),
        idNumber = fieldValues[Constants.APPLICANT_ID_NUMBER]!!.text.toString(),
        idExpeditionDate = fieldValues[Constants.APPLICANT_ID_EXPEDITION_DATE]!!.text.toString(),
        countryOfBirth = fieldValues[Constants.APPLICANT_COUNTRY_OF_BIRTH]!!.text.toString(),
        departmentOfBirth = fieldValues[Constants.APPLICANT_DEPARTMENT_OF_BIRTH]!!.text.toString(),
        cityOfBirth = fieldValues[Constants.APPLICANT_CITY_OF_BIRTH]!!.text.toString(),
        dateOfBirth = fieldValues[Constants.APPLICANT_DATE_OF_BIRTH]!!.text.toString(),
        addressCountry = fieldValues[Constants.APPLICANT_ADDRESS_COUNTRY]!!.text.toString(),
        addressDepartment = fieldValues[Constants.APPLICANT_ADDRESS_DEPARTMENT]!!.text.toString(),
        addressCity = fieldValues[Constants.APPLICANT_ADDRESS_CITY]!!.text.toString(),
        addressDistrict = fieldValues[Constants.APPLICANT_ADDRESS_DISTRICT]!!.text.toString(),
        addressSettlement = fieldValues[Constants.APPLICANT_ADDRESS_SETTLEMENT]!!.text.toString(),
        addressNeighborhood = fieldValues[Constants.APPLICANT_ADDRESS_NEIGHBORHOOD]!!.text.toString(),
        addressZone = fieldValues[Constants.APPLICANT_ADDRESS_ZONE]!!.text.toString(),
        address = fieldValues[Constants.APPLICANT_ADDRESS]!!.text.toString(),
        addressDetails = fieldValues[Constants.APPLICANT_ADDRESS_DETAILS]!!.text.toString(),
        phoneNumber1 = fieldValues[Constants.APPLICANT_PHONE_NUMBER_1]!!.text.toString(),
        phoneNumber2 = fieldValues[Constants.APPLICANT_PHONE_NUMBER_2]!!.text.toString(),
        landline = fieldValues[Constants.APPLICANT_LANDLINE]!!.text.toString(),
        email = fieldValues[Constants.APPLICANT_EMAIL]!!.text.toString(),
        notificationApproval = fieldValues[Constants.APPLICANT_NOTIFICATION_APPROVAL]!!.text.toString(),
        notificationCountry = fieldValues[Constants.APPLICANT_NOTIFICATION_COUNTRY]!!.text.toString(),
        notificationDepartment = fieldValues[Constants.APPLICANT_NOTIFICATION_DEPARTMENT]!!.text.toString(),
        notificationCity = fieldValues[Constants.APPLICANT_NOTIFICATION_CITY]!!.text.toString(),
        notificationAddress = fieldValues[Constants.APPLICANT_NOTIFICATION_ADDRESS]!!.text.toString(),
        sex = fieldValues[Constants.APPLICANT_SEX]!!.text.toString(),
        gender = fieldValues[Constants.APPLICANT_GENDER]!!.text.toString(),
        sexualOrientation = fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION]!!.text.toString(),
        age = fieldValues[Constants.APPLICANT_AGE]!!.text.toString(),
        identifyingTrait = fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT]!!.text.toString(),
        identifyingTraitAmount = fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_AMOUNT]!!.text.toString(),
        disabilityStatus = fieldValues[Constants.APPLICANT_DISABILITY_STATUS]!!.text.toString(),
        disabilityType = fieldValues[Constants.APPLICANT_DISABILITY_TYPE]!!.text.toString(),
        ethnicGroupStatus = fieldValues[Constants.APPLICANT_ETHNIC_GROUP_STATUS]!!.text.toString(),
        ethnicGroupType = fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE]!!.text.toString(),
        indigenousGroup = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP]!!.text.toString(),
        indigenousGroupReservation = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION]!!.text.toString(),
        indigenousGroupReservationCommunity = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY]!!.text.toString(),
        indigenousGroupPartial = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_PARTIAL]!!.text.toString(),
        indigenousGroupNoRegistry = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY]!!.text.toString(),
        africanAmericanCommunity = fieldValues[Constants.APPLICANT_AFRICAN_AMERICAN_COMMUNITY]!!.text.toString(),
        organizationMembershipStatus = fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS]!!.text.toString(),
        organizationType = fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE]!!.text.toString(),
        organizationMembershipOther = fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER]!!.text.toString(),
        organizationName = fieldValues[Constants.APPLICANT_ORGANIZATION_NAME]!!.text.toString(),
        legalRepresentativeStatus = fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS]!!.text.toString(),
        legalRepresentativeName = fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_NAME]!!.text.toString(),
        provisionalMeasuresStatus = fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS]!!.text.toString(),
        provisionalMeasuresType = fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE]!!.text.toString()
    )

    val thirdParty = ThirdParty(
        namesAndLastNames = fieldValues[Constants.THIRD_PARTY_NAMES_AND_LASTNAMES]!!.text.toString(),
        addressCountry = fieldValues[Constants.THIRD_PARTY_ADDRESS_COUNTRY]!!.text.toString(),
        addressDepartment = fieldValues[Constants.THIRD_PARTY_ADDRESS_DEPARTMENT]!!.text.toString(),
        addressCity = fieldValues[Constants.THIRD_PARTY_ADDRESS_CITY]!!.text.toString(),
        addressDistrict = fieldValues[Constants.THIRD_PARTY_ADDRESS_DISTRICT]!!.text.toString(),
        addressSettlement = fieldValues[Constants.THIRD_PARTY_ADDRESS_SETTLEMENT]!!.text.toString(),
        addressNeighborhood = fieldValues[Constants.THIRD_PARTY_ADDRESS_NEIGHBORHOOD]!!.text.toString(),
        address = fieldValues[Constants.THIRD_PARTY_ADDRESS]!!.text.toString(),
        addressDetails = fieldValues[Constants.THIRD_PARTY_ADDRESS_DETAILS]!!.text.toString(),
        phoneNumber1 = fieldValues[Constants.THIRD_PARTY_PHONE_NUMBER_1]!!.text.toString(),
        phoneNumber2 = fieldValues[Constants.THIRD_PARTY_PHONE_NUMBER_2]!!.text.toString(),
        landline = fieldValues[Constants.THIRD_PARTY_LANDLINE]!!.text.toString(),
        email = fieldValues[Constants.THIRD_PARTY_EMAIL]!!.text.toString(),
        notificationApproval = fieldValues[Constants.THIRD_PARTY_NOTIFICATION_APPROVAL]!!.text.toString()
    )

    return Form(
        formNumber = fieldValues[Constants.FORM_NUMBER]!!.text.toString(),
        formDate = fieldValues[Constants.FORM_DATE]!!.text.toString(),
        formOffice = fieldValues[Constants.FORM_OFFICE]!!.text.toString(),
        formFillDate = fieldValues[Constants.FORM_FILL_DATE]!!.text.toString(),
        formCountry = fieldValues[Constants.FORM_COUNTRY]!!.text.toString(),
        formDepartment = fieldValues[Constants.FORM_DEPARTMENT]!!.text.toString(),
        formCity = fieldValues[Constants.FORM_CITY]!!.text.toString(),
        applicantData = applicant,
        thirdPartyData = thirdParty
    )
}

