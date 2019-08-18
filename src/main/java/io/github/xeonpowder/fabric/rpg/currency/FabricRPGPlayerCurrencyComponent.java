package io.github.xeonpowder.fabric.rpg.currency;

import nerdhub.cardinal.components.api.component.Component;
import nerdhub.cardinal.components.api.util.sync.EntitySyncedComponent;

/**
 * FabricRPGCurrency
 */
public interface FabricRPGPlayerCurrencyComponent extends Component, EntitySyncedComponent {
    public FabricRPGPlayerCurrency getCurrency();
}