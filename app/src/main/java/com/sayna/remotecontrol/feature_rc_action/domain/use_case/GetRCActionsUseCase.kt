package com.sayna.remotecontrol.feature_rc_action.domain.use_case

import com.sayna.remotecontrol.feature_rc_action.domain.model.RCAction
import com.sayna.remotecontrol.feature_rc_action.domain.repository.RCActionRepository
import com.sayna.remotecontrol.feature_rc_action.domain.util.OrderType
import com.sayna.remotecontrol.feature_rc_action.domain.util.RCActionOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRCActionsUseCase(
    private val repository: RCActionRepository
) {
    operator fun invoke(
        rcActionOrder: RCActionOrder = RCActionOrder.Title(OrderType.Descending)
    ): Flow<List<RCAction>> {
        return repository.GetRCActions().map { rcActions ->
            when(rcActionOrder.orderType) {
                is OrderType.Ascending -> {
                    when(rcActionOrder) {
                        is RCActionOrder.Title -> rcActions.sortedBy { it.title.lowercase() }
                        is RCActionOrder.ID -> rcActions.sortedBy { it.id }
                    }
                }
                is OrderType.Descending -> {
                    when(rcActionOrder) {
                        is RCActionOrder.Title -> rcActions.sortedByDescending { it.title.lowercase() }
                        is RCActionOrder.ID -> rcActions.sortedByDescending { it.id }
                    }
                }
            }
        }
    }
}