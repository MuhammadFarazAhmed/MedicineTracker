package com.example.medicinetracker.model


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("problems")
    val problems: List<Problem?>?
) {
    data class Problem(
        @SerializedName("Diabetes")
        val diabetes: List<Diabete?>?,
        @SerializedName("Asthma")
        val asthma: List<Asthma?>?
    ) {
        data class Diabete(
            @SerializedName("medications")
            val medications: List<Medication?>?,
            @SerializedName("labs")
            val labs: List<Lab?>?
        ) {
            data class Medication(
                @SerializedName("medicationsClasses")
                val medicationsClasses: List<MedicationsClasse?>?
            ) {
                data class MedicationsClasse(
                    @SerializedName("className")
                    val className: List<ClassName?>?,
                    @SerializedName("className2")
                    val className2: List<ClassName2?>?
                ) {
                    data class ClassName(
                        @SerializedName("associatedDrug")
                        val associatedDrug: List<AssociatedDrug?>?,
                        @SerializedName("associatedDrug#2")
                        val associatedDrug2: List<AssociatedDrug2?>?
                    ) {
                        data class AssociatedDrug(
                            @SerializedName("name")
                            val name: String?,
                            @SerializedName("dose")
                            val dose: String?,
                            @SerializedName("strength")
                            val strength: String?
                        )

                        data class AssociatedDrug2(
                            @SerializedName("name")
                            val name: String?,
                            @SerializedName("dose")
                            val dose: String?,
                            @SerializedName("strength")
                            val strength: String?
                        )
                    }

                    data class ClassName2(
                        @SerializedName("associatedDrug")
                        val associatedDrug: List<AssociatedDrug?>?,
                        @SerializedName("associatedDrug#2")
                        val associatedDrug2: List<AssociatedDrug2?>?
                    ) {
                        data class AssociatedDrug(
                            @SerializedName("name")
                            val name: String?,
                            @SerializedName("dose")
                            val dose: String?,
                            @SerializedName("strength")
                            val strength: String?
                        )

                        data class AssociatedDrug2(
                            @SerializedName("name")
                            val name: String?,
                            @SerializedName("dose")
                            val dose: String?,
                            @SerializedName("strength")
                            val strength: String?
                        )
                    }
                }
            }

            data class Lab(
                @SerializedName("missing_field")
                val missingField: String?
            )
        }

        class Asthma
    }
}

fun Response.toProblemEntities(): List<ProblemEntity> {
    val entities = mutableListOf<ProblemEntity>()

    problems?.forEachIndexed { index, problem ->
        val diabetes = problem?.diabetes
        val asthma = problem?.asthma


        val diabetesMedications = diabetes?.flatMap { diabete ->
            diabete?.medications?.flatMap { medication ->
                medication?.medicationsClasses?.flatMap { medicationsClass ->
                    medicationsClass?.className?.flatMap { className ->
                        className?.associatedDrug?.map { associatedDrug ->
                            MedicationEntity(
                                problemId = index.toLong(),
                                name = associatedDrug?.name ?:"",
                                dose = associatedDrug?.dose,
                                strength = associatedDrug?.strength ?: ""
                            )
                        } ?: emptyList()
                    } ?: emptyList()
                } ?: emptyList()
            } ?: emptyList()
        } ?: emptyList()

        val diabetesLabs = diabetes?.flatMap { diabete ->
            diabete?.labs?.map { lab ->
                LabEntity(
                    problemId = index.toLong(),
                    missingField = lab?.missingField ?:""
                )
            } ?: emptyList()
        } ?: emptyList()


        entities.add(
            ProblemEntity(
                problemType = "Diabetes",
                medications = diabetesMedications,
                labs = diabetesLabs
            )
        )

        //asthma has no records
    }

    return entities
}