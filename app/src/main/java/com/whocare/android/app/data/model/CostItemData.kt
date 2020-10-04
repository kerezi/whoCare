package com.whocare.android.app.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

data class CostItemData (
    @Embedded val costItem: CostItem,
    @Relation(
        parentColumn = "costId",
        entityColumn = "id"
    ) val cost: Cost,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    ) val product: Product,
    @Relation(
        parentColumn = "priceId",
        entityColumn = "id"
    ) val price: Price,
    @Relation(
        parentColumn = "ownerId",
        entityColumn = "id"
    ) val owner: NameId,
    @Relation(
        parentColumn = "payerId",
        entityColumn = "id"
    ) val payer: NameId,
    @Relation(
        parentColumn = "costItemId",
        entityColumn = "costItemCategoryId",
        associateBy = Junction(CostItemCategoryCrossRef::class)
    ) val costItemCategories: List<CostItemCategory>

)