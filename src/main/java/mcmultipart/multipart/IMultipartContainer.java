package mcmultipart.multipart;

import java.util.Collection;
import java.util.UUID;

import mcmultipart.capabilities.ISlottedCapabilityProvider;
import mcmultipart.util.IWorldLocation;

/**
 * Interface that represents an object that can contain multiparts.
 */
public interface IMultipartContainer extends IWorldLocation, ISlottedCapabilityProvider {

    /**
     * Gets all the parts in this container.
     */
    public Collection<? extends IMultipart> getParts();

    /**
     * Gets the part in the specificed {@link PartSlot}.
     */
    public ISlottedPart getPartInSlot(PartSlot slot);

    /**
     * Checks whether or not a specific part can be added.
     */
    public boolean canAddPart(IMultipart part);

    /**
     * Checks whether or not a specific part can be replaced.
     */
    public boolean canReplacePart(IMultipart oldPart, IMultipart newPart);

    /**
     * Adds a part to the container. You MUST check if {@link IMultipartContainer#canAddPart(IMultipart)} or
     * {@link IMultipartContainer#canReplacePart(IMultipart, IMultipart)} return true before calling this method.
     */
    public void addPart(IMultipart part);

    /**
     * Removes a part from this container.
     */
    public void removePart(IMultipart part);

    /**
     * Gets the UUID of a specific part. Used to synchronize the server and the client.
     */
    public UUID getPartID(IMultipart part);

    /**
     * Gets a part from its UUID. Used to synchronize the server and the client.
     */
    public IMultipart getPartFromID(UUID id);

    /**
     * Adds a part with a specific UUID. Used when adding a part on the clientside through a placement packet. Do not call this manually.
     */
    public void addPart(UUID id, IMultipart part);

    /**
     * Performs an occlusion test against all the parts in this container except for the specified ones, and possibly tests against the
     * container itself.
     */
    public boolean occlusionTest(IMultipart part, IMultipart... ignored);

    public static interface IMultipartContainerListener {

        public void onAddPartPre(IMultipart part);

        public void onAddPartPost(IMultipart part);

        public void onRemovePartPre(IMultipart part);

        public void onRemovePartPost(IMultipart part);

    }

}
