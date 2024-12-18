package co.sublimetech.lideres.statistics.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.sublimetech.lideres.core.design_system.MyBarChart
import co.sublimetech.lideres.core.design_system.MyDonutChart
import co.sublimetech.lideres.core.design_system.MyPieChart
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

    val domicilioData = listOf(
        Triple("Urbano", 60.0, MaterialTheme.colorScheme.primary),
        Triple("Rural", 40.0, MaterialTheme.colorScheme.tertiary)
    )

    val amenazaData = listOf(
        Pair("Amenaza", 3.0),
        Pair("Atentado", 4.0),
        Pair("Secuestro", 2.0),
        Pair("Homicidio", 1.0),
        Pair("Extorsión", 1.0),
        Pair("Reclutamiento ilegal", 1.0),
        Pair("Otra", 0.0),
    ).sortedByDescending { it.second }

    val sexoData = listOf(
        Triple("Hombre", 40.0, MaterialTheme.colorScheme.primary),
        Triple("Mujer", 56.0, MaterialTheme.colorScheme.secondary),
        Triple("Intersexual", 4.0, MaterialTheme.colorScheme.tertiary)
    )

    val tendenciaTemporal = listOf(
        Pair("Ene", 30.0),
        Pair("Ene", 0.0),
        Pair("Ene", 30.0),
        Pair("Mar", 60.0),
        Pair("Mar", 90.0),
        Pair("Feb", 120.0),
        Pair("Feb", 143.0),
    )


    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(1),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            //  MyLineChart(
            //      data = tendenciaTemporal,
            //      title = "Tendencia temporal",
            //      maxHeight = 500.dp
            //  )
        }
        item {
            MyBarChart(
                title = "Situación de riesgo",
                data = amenazaData,
                maxHeight = 400.dp
            )
        }
        item {
            MyPieChart(
                data = domicilioData,
                title = "Zona de Domicilio",
                chartSize = 250.dp, // Tamaño del gráfico
                maxHeight = 400.dp, // Altura máxima del Card
            )
        }
        item {
            MyDonutChart(
                data = sexoData,
                title = "Sexo",
                maxHeight = 500.dp,
                chartSize = 250.dp,
                gapAngle = 7f,
                donutThickness = 20.dp
            )
        }
        item{
            Button(
                onClick = { onAction(StatisticsAction.OnFormClick) }
            ) {
                Text("Go To Form")
            }
        }
    }

}
