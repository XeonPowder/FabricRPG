package io.github.xeonpowder.fabric.rpg.portalnetwork;

import nerdhub.cardinal.components.api.component.Component;

/**
 * PortalNetworkComponent
 */
public interface PortalNetworkComponent<T> extends Component {
    PortalNetwork<T> getPortalNetwork();

}