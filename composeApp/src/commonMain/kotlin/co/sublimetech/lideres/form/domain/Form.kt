package co.sublimetech.lideres.form.domain


import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class Form(
    @PrimaryKey(autoGenerate = false)
    @SerialName("numero_de_formulario")
    val formNumber: String,
    @SerialName("fecha_de_formulario")
    val formDate: String,
    @SerialName("oficina_de_formulario")
    val formOffice: String,
    @SerialName("fecha_de_diligenciamiento")
    val formFillDate: String,
    @SerialName("pais")
    val formCountry: String,
    @SerialName("departamento")
    val formDepartment: String,
    @SerialName("ciudad")
    val formCity: String,
    @SerialName("solicitante")
    val applicantData: Applicant,
    @SerialName("tercero")
    val thirdPartyData: ThirdParty,
)

@Serializable
data class Applicant(
    @SerialName("primer_nombre")
    val firstName: String,
    @SerialName("segundo_nombre")
    val secondName: String,
    @SerialName("primer_apellido")
    val firstLastName: String,
    @SerialName("segundo_apellido")
    val secondLastName: String,
    @SerialName("nombre_identitario")
    val identifyingName: String,
    @SerialName("tipo_de_documento")
    val idType: String,
    @SerialName("numero_de_documento")
    val idNumber: String,
    @SerialName("fecha_de_expedicion")
    val idExpeditionDate: String,
    @SerialName("pais_de_nacimiento")
    val countryOfBirth: String,
    @SerialName("departamento_de_nacimiento")
    val departmentOfBirth: String,
    @SerialName("ciudad_de_nacimiento")
    val cityOfBirth: String,
    @SerialName("fecha_de_nacimiento")
    val dateOfBirth: String,
    @SerialName("pais_de_domicilio")
    val addressCountry: String,
    @SerialName("departamento_de_domicilio")
    val addressDepartment: String,
    @SerialName("ciudad_de_domicilio")
    val addressCity: String,
    @SerialName("distrito_de_domicilio")
    val addressDistrict: String,
    @SerialName("vereda_de_domicilio")
    val addressSettlement: String,
    @SerialName("barrio_de_domicilio")
    val addressNeighborhood: String,
    @SerialName("zona_de_domicilio")
    val addressZone: String,
    @SerialName("direccion_de_domicilio")
    val address: String,
    @SerialName("detalles_de_domicilio")
    val addressDetails: String,
    @SerialName("telefono_1")
    val phoneNumber1: String,
    @SerialName("telefono_2")
    val phoneNumber2: String,
    @SerialName("telefono_fijo")
    val landline: String,
    @SerialName("correo_electronico")
    val email: String,
    @SerialName("autorizacion_de_notificaciones")
    val notificationApproval: String,
    @SerialName("pais_de_notificacion")

    val notificationCountry: String,
    @SerialName("departamento_de_notificacion")
    val notificationDepartment: String,
    @SerialName("ciudad_de_notificacion")
    val notificationCity: String,
    @SerialName("direccion_de_notificacion")
    val notificationAddress: String,
    @SerialName("sexo")
    val sex: String,
    @SerialName("genero")
    val gender: String,
    @SerialName("orientacion_sexual")
    val sexualOrientation: String,
    @SerialName("edad")
    val age: String,
    @SerialName("rasgo_identitario")
    val identifyingTrait: String,
    @SerialName("cantidad_de_rasgos")
    val identifyingTraitAmount: String,
    @SerialName("estado_de_discapacidad")
    val disabilityStatus: String,
    @SerialName("tipo_de_discapacidad")
    val disabilityType: String,
    @SerialName("estado_de_grupo_etnico")
    val ethnicGroupStatus: String,
    @SerialName("tipo_de_grupo_etnico")
    val ethnicGroupType: String,
    @SerialName("grupo_indigena")
    val indigenousGroup: String,
    @SerialName("reserva_indigena")
    val indigenousGroupReservation: String,
    @SerialName("comunidad_de_reserva")
    val indigenousGroupReservationCommunity: String,
    @SerialName("parcialidad_indigena")
    val indigenousGroupPartial: String,
    @SerialName("sin_registro")
    val indigenousGroupNoRegistry: String,
    @SerialName("comunidad_afroamericana")
    val africanAmericanCommunity: String,
    @SerialName("estado_de_afiliacion_organizacional")
    val organizationMembershipStatus: String,
    @SerialName("tipo_de_organizacion")
    val organizationType: String,
    @SerialName("otra_afiliacion_organizacional")
    val organizationMembershipOther: String,
    @SerialName("nombre_de_organizacion")
    val organizationName: String,
    @SerialName("estado_de_representacion_legal")
    val legalRepresentativeStatus: String,
    @SerialName("nombre_de_representante_legal")
    val legalRepresentativeName: String,
    @SerialName("estado_de_medidas_provisionales")
    val provisionalMeasuresStatus: String,
    @SerialName("tipo_de_medidas_provisionales")
    val provisionalMeasuresType: String,
)

@Serializable
data class ThirdParty(
    @SerialName("nombres_y_apellidos_de_tercero")
    val namesAndLastNames: String,
    @SerialName("pais_de_domicilio_de_tercero")
    val addressCountry: String,
    @SerialName("departamento_de_domicilio_de_tercero")
    val addressDepartment: String,
    @SerialName("ciudad_de_domicilio_de_tercero")

    val addressCity: String,
    @SerialName("distrito_de_domicilio_de_tercero")
    val addressDistrict: String,
    @SerialName("vereda_de_domicilio_de_tercero")
    val addressSettlement: String,
    @SerialName("barrio_de_domicilio_de_tercero")
    val addressNeighborhood: String,
    @SerialName("direccion_de_domicilio_de_tercero")
    val address: String,
    @SerialName("detalles_de_domicilio_de_tercero")
    val addressDetails: String,
    @SerialName("telefono_1_de_tercero")
    val phoneNumber1: String,
    @SerialName("telefono_2_de_tercero")
    val phoneNumber2: String,
    @SerialName("telefono_fijo_de_tercero")
    val landline: String,
    @SerialName("correo_electronico_de_tercero")
    val email: String,
    @SerialName("autorizacion_de_notificaciones_de_tercero")
    val notificationApproval: String,
)
