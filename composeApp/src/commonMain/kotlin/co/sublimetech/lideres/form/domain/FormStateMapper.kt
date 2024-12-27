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
        idType = when {
            fieldValues[Constants.APPLICANT_ID_NATIONAL_ID]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ID_NATIONAL_ID]!!.text.toString()

            fieldValues[Constants.APPLICANT_ID_FOREIGN_ID]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ID_FOREIGN_ID]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ID_NUIP]!!.text.toString()
        },
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
        addressZone = when {
            fieldValues[Constants.APPLICANT_ADDRESS_ZONE_URBAN]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ADDRESS_ZONE_URBAN]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ADDRESS_ZONE_RURAL]!!.text.toString()
        },
        address = fieldValues[Constants.APPLICANT_ADDRESS]!!.text.toString(),
        addressDetails = fieldValues[Constants.APPLICANT_ADDRESS_DETAILS]!!.text.toString(),
        phoneNumber1 = fieldValues[Constants.APPLICANT_PHONE_NUMBER_1]!!.text.toString(),
        phoneNumber2 = fieldValues[Constants.APPLICANT_PHONE_NUMBER_2]!!.text.toString(),
        landline = fieldValues[Constants.APPLICANT_LANDLINE]!!.text.toString(),
        email = fieldValues[Constants.APPLICANT_EMAIL]!!.text.toString(),
        notificationApproval = when {
            fieldValues[Constants.APPLICANT_NOTIFICATION_APPROVAL_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_NOTIFICATION_APPROVAL_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_NOTIFICATION_APPROVAL_NEGATIVE]!!.text.toString()
        },
        notificationCountry = fieldValues[Constants.APPLICANT_NOTIFICATION_COUNTRY]!!.text.toString(),
        notificationDepartment = fieldValues[Constants.APPLICANT_NOTIFICATION_DEPARTMENT]!!.text.toString(),
        notificationCity = fieldValues[Constants.APPLICANT_NOTIFICATION_CITY]!!.text.toString(),
        notificationAddress = fieldValues[Constants.APPLICANT_NOTIFICATION_ADDRESS]!!.text.toString(),
        sex = when {
            fieldValues[Constants.APPLICANT_SEX_MEN]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_SEX_MEN]!!.text.toString()

            fieldValues[Constants.APPLICANT_SEX_WOMEN]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_SEX_WOMEN]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_SEX_INTERSEXUAL]!!.text.toString()
        },
        gender = when {
            fieldValues[Constants.APPLICANT_GENDER_MALE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_GENDER_MALE]!!.text.toString()

            fieldValues[Constants.APPLICANT_GENDER_FEMALE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_GENDER_FEMALE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_GENDER_TRANSGENDER]!!.text.toString()
        },
        sexualOrientation = when {
            fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION_HETEROSEXUAL]!!.text.toString()
                .isNotBlank() ->
                fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION_HETEROSEXUAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION_HOMOSEXUAL]!!.text.toString()
                .isNotBlank() ->
                fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION_HOMOSEXUAL]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_SEXUAL_ORIENTATION_BISEXUAL]!!.text.toString()
        },
        age = when {
            fieldValues[Constants.APPLICANT_AGE_CHILD]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_AGE_CHILD]!!.text.toString()

            fieldValues[Constants.APPLICANT_AGE_TEENAGER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_AGE_TEENAGER]!!.text.toString()

            fieldValues[Constants.APPLICANT_AGE_YOUNG_ADULT]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_AGE_YOUNG_ADULT]!!.text.toString()

            fieldValues[Constants.APPLICANT_AGE_ADULT]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_AGE_ADULT]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_AGE_ELDERLY]!!.text.toString()
        },
        identifyingTrait = when {
            fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_FATHER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_FATHER]!!.text.toString()

            fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_MOTHER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_MOTHER]!!.text.toString()

            fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_CARETAKER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_CARETAKER]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_PEOPLE_UNDER_CARE]!!.text.toString()
        },
        identifyingTraitAmount = fieldValues[Constants.APPLICANT_IDENTIFYING_TRAIT_AMOUNT]!!.text.toString(),
        disabilityStatus = when {
            fieldValues[Constants.APPLICANT_DISABILITY_STATUS_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_STATUS_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_DISABILITY_STATUS_NEGATIVE]!!.text.toString()
        },
        disabilityType = when {
            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_PHYSICAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_PHYSICAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_HEARING]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_HEARING]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_VISUAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_VISUAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_BLINDNESS]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_BLINDNESS]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_MENTAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_MENTAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_INTELLECTUAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_INTELLECTUAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_DISABILITY_TYPE_LITTLE_PEOPLE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_LITTLE_PEOPLE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_DISABILITY_TYPE_MULTIPLE]!!.text.toString()
        },
        ethnicGroupStatus = when {
            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_STATUS_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_STATUS_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_STATUS_NEGATIVE]!!.text.toString()
        },
        ethnicGroupType = when {
            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_INDIGENOUS]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_INDIGENOUS]!!.text.toString()

            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_NEGRO]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_NEGRO]!!.text.toString()

            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_AFRICAN_AMERICAN]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_AFRICAN_AMERICAN]!!.text.toString()

            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_SAN_ANDRES]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_SAN_ANDRES]!!.text.toString()

            fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_BOLIVAR]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_BOLIVAR]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ETHNIC_GROUP_TYPE_GIPSY]!!.text.toString()
        },
        indigenousGroup = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP]!!.text.toString(),
        indigenousGroupReservation = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION]!!.text.toString(),
        indigenousGroupReservationCommunity = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_RESERVATION_COMMUNITY]!!.text.toString(),
        indigenousGroupPartial = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_PARTIAL]!!.text.toString(),
        indigenousGroupNoRegistry = fieldValues[Constants.APPLICANT_INDIGENOUS_GROUP_NO_REGISTRY]!!.text.toString(),
        africanAmericanCommunity = fieldValues[Constants.APPLICANT_AFRICAN_AMERICAN_COMMUNITY]!!.text.toString(),
        organizationMembershipStatus = when {
            fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_STATUS_NEGATIVE]!!.text.toString()
        },
        organizationType = when {
            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_SOCIAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_SOCIAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_GUILD]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_GUILD]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_CIVIL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_CIVIL]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_COMMUNAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_COMMUNAL]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_FARMER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_FARMER]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_VICTIMS]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_VICTIMS]!!.text.toString()

            fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_HUMANS_RIGHT_WATCH]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_HUMANS_RIGHT_WATCH]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_TYPE_OTHER]!!.text.toString()
        },
        organizationMembershipOther = fieldValues[Constants.APPLICANT_ORGANIZATION_MEMBERSHIP_OTHER]!!.text.toString(),
        organizationName = fieldValues[Constants.APPLICANT_ORGANIZATION_NAME]!!.text.toString(),
        legalRepresentativeStatus = when {
            fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_STATUS_NEGATIVE]!!.text.toString()
        },
        legalRepresentativeName = fieldValues[Constants.APPLICANT_ORGANIZATION_LEGAL_REPRESENTATIVE_ID_NUMBER]!!.text.toString(),
        provisionalMeasuresStatus = when {
            fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS_POSITIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_STATUS_NEGATIVE]!!.text.toString()
        },
        provisionalMeasuresType = when {
            fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COMMISSION]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COMMISSION]!!.text.toString()

            fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COURT]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_AMERICAN_COURT]!!.text.toString()
            else ->
                fieldValues[Constants.APPLICANT_PROVISIONAL_MEASURES_TYPE_NATIONAL_JUDGE]!!.text.toString()
        },
        riskOrThreatReport = fieldValues[Constants.RISK_OR_THREAT_REPORT]!!.text.toString(),
        riskSituationType = when {
            fieldValues[Constants.RISK_SITUATION_TYPE_THREAT]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_THREAT]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_TYPE_ATTACK]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_ATTACK]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_TYPE_KIDNAPPING]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_KIDNAPPING]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_TYPE_FAMILY_MEMBER_HOMICIDE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_FAMILY_MEMBER_HOMICIDE]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_TYPE_EXTORTION]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_EXTORTION]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_TYPE_RECRUITMENT]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_TYPE_RECRUITMENT]!!.text.toString()
            else ->
                fieldValues[Constants.RISK_SITUATION_TYPE_OTHER]!!.text.toString()
        },
        riskSituationOther = fieldValues[Constants.RISK_SITUATION_OTHER_ACTUAL]!!.text.toString(),
        riskSituationMeansType =when {
            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_BY_PHONE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_BY_PHONE]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_VERBAL]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_VERBAL]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_WRITTEN]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_WRITTEN]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_THROUGH_THIRD_PARTY]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_THROUGH_THIRD_PARTY]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_SYMBOLIC]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_SYMBOLIC]!!.text.toString()

            fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_SOCIAL_MEDIA]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_SOCIAL_MEDIA]!!.text.toString()
            else ->
                fieldValues[Constants.RISK_SITUATION_MEANS_TYPE_OTHER]!!.text.toString()
        },
        riskSituationMeansOther = fieldValues[Constants.RISK_SITUATION_MEANS_OTHER_ACTUAL]!!.text.toString(),
        protectionAndPreventionGroup = when {
            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_1]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_1]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_2]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_2]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_3]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_3]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_4]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_4]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_5]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_5]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_6]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_6]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_7]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_7]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_8]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_8]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_9]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_9]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_10]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_10]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_11]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_11]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_12]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_12]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_13]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_13]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_14]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_14]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_15]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_15]!!.text.toString()

            fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_16]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_16]!!.text.toString()
            else ->
                fieldValues[Constants.PROTECTION_AND_PREVENTION_GROUP_17]!!.text.toString()
                                            },
        patrioticUnionComunistParty = fieldValues[Constants.PATRIOTIC_UNION_AND_COMMUNIST_PARTY_SURVIVOR]!!.text.toString(),
        especialSecurityProtection = fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP]!!.text.toString(),
        especialSecurityProtectionType = when {
            fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE_LEADER]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE_LEADER]!!.text.toString()

            fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE_REPRESENTATIVE]!!.text.toString().isNotBlank() ->
                fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE_REPRESENTATIVE]!!.text.toString()

            else ->
                fieldValues[Constants.ESPECIAL_SECURITY_AND_PROTECTION_GROUP_TYPE_MEMBER]!!.text.toString()
        },
        dataConsentAcceptance = fieldValues[Constants.DATA_CONSENT_ACCEPTANCE]!!.text.toString(),
        nationalPolicePreventiveMeasuresAcceptance = fieldValues[Constants.NATIONAL_POLICE_PREVENTIVE_MEASURES_ACCEPTANCE]!!.text.toString(),
        cerremWomenAcceptance = fieldValues[Constants.CERREM_WOMEN_ACCEPTANCE]!!.text.toString(),
        applicantFullName = fieldValues[Constants.APPLICANT_FULL_NAME]!!.text.toString(),
        applicantSignature = fieldValues[Constants.APPLICANT_SIGNATURE]!!.text.toString(),
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
        notificationApproval = when {
            fieldValues[Constants.THIRD_PARTY_NOTIFICATION_APPROVAL_POSITIVE]!!.text.toString()
                .isNotBlank() ->
                fieldValues[Constants.THIRD_PARTY_NOTIFICATION_APPROVAL_POSITIVE]!!.text.toString()

            else ->
                fieldValues[Constants.THIRD_PARTY_NOTIFICATION_APPROVAL_NEGATIVE]!!.text.toString()
        }
    )

    val enroller = Enroller(
        applicantIsFormEnroller = fieldValues[Constants.APPLICANT_IS_FORM_ENROLLER]!!.text.toString(),
        formEnrollerNameAndLastName = fieldValues[Constants.FORM_ENROLLER_NAME_AND_LASTNAME]!!.text.toString(),
        enrollerEntityName = fieldValues[Constants.APPLICANT_IS_FORM_ENROLLER]!!.text.toString(),
        formEnrollerFormNumber = fieldValues[Constants.FORM_ENROLLER_PHONE_NUMBER]!!.text.toString(),
        formEnrollerFormEmail = fieldValues[Constants.FORM_ENROLLER_EMAIL]!!.text.toString(),
    )

    return Form(
        formNumber = fieldValues[Constants.FORM_NUMBER]!!.text.toString(),
        formDate = fieldValues[Constants.FORM_DATE]!!.text.toString(),
        formOffice = fieldValues[Constants.FORM_OFFICE]!!.text.toString(),
        formFillDate = fieldValues[Constants.FORM_FILL_DATE]!!.text.toString(),
        formCountry = fieldValues[Constants.FORM_COUNTRY]!!.text.toString(),
        formDepartment = fieldValues[Constants.FORM_DEPARTMENT]!!.text.toString(),
        formCity = fieldValues[Constants.FORM_CITY]!!.text.toString(),
        publicServantUnpReceiverName = fieldValues[Constants.PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_NAME]!!.text.toString(),
        publicServantUnpReceiverEmail = fieldValues[Constants.PUBLIC_SERVANT_OR_UNP_FORM_RECEIVER_EMAIL]!!.text.toString(),
        filedInIdentifier = fieldValues[Constants.FILED_IN_IDENTIFIER]!!.text.toString(),
        applicantData = applicant,
        thirdPartyData = thirdParty,
        enrollerData = enroller
    )
}

