package com.example.medicinetracker.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicinetracker.model.MedicationEntity
import com.example.medicinetracker.model.ProblemEntity
import com.example.medicinetracker.ui.theme.AccentColor
import com.example.medicinetracker.ui.theme.Secondary
import com.example.medicinetracker.ui.theme.TextColor

class SampleUserProvider : PreviewParameterProvider<ProblemEntity> {
    override val values = sequenceOf(ProblemEntity(problemType = "Diabete"))
}


@Composable
fun ProblemListItem(
    @PreviewParameter(SampleUserProvider::class) problem: ProblemEntity,
    onClick: () -> Unit
) {

    Card(modifier = Modifier.fillMaxWidth().padding(8.dp), backgroundColor = Secondary, elevation = 10.dp) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth().clickable {
                    onClick()
                }
        ) {
            Text(
                text = problem.problemType,
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            problem.medications.forEachIndexed { _ , item ->
                NestedItem(item)
            }
        }
    }
}

@Composable
fun NestedItem(item: MedicationEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        backgroundColor = AccentColor,
        elevation = 10.dp
    ) {
        Column(modifier = Modifier
            .padding(12.dp)) {
            Text(
                text = "Name: ${item.name}",
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Dose : ${item.dose}",
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Strength : ${item.strength}",
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}


@Composable
@Preview
fun ListItemPreview() {
    Card(modifier = Modifier.fillMaxWidth(), backgroundColor = Secondary, elevation = 10.dp) {

        Column {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp),
                text = "Diabetes",
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            val list = listOf(
                MedicationEntity(name = "Aspirin", dose = "2 times", strength = "500 mg"),
                MedicationEntity(name = "Insulin", dose = "2 times", strength = "500 mg")
            )

            NestedItem(item = list[0])
        }
    }
}

