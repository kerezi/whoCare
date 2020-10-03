package com.whocare.android.app.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CostItemData (
    @Embedded val costItem: CostItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "costId"
    ) val cost: Cost,
    @Relation(
        parentColumn = "id",
        entityColumn = "productPriceId"
    ) val productPrice: ProductPrice,
    @Relation(
        parentColumn = "id",
        entityColumn = "ownerId"
    ) val owner: NameId,
    @Relation(
        parentColumn = "id",
        entityColumn = "payerId"
    ) val payer: NameId,
    @Relation(
        parentColumn = "costItemId",
        entityColumn = "costCategoryId",
        associateBy = Junction(CostItemCategoryCrossRef::class)
    ) val costItemCategories: List<CostItemCategory>

)