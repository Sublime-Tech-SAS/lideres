package co.sublimetech.lideres.form.presentation

sealed interface FormAction {
    data object OnStatisticsClick : FormAction
    data object OnSaveFormClick : FormAction
    data object OnGetFormClick : FormAction
    data object OnGetFormsClick : FormAction
}