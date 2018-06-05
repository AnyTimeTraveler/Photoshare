export const CURRENT_COLLECTION_SET_ITEMS = 'CURRENT_COLLECTION_SET_ITEMS';
export const CURRENT_COLLECTION_SET_CURRENT_ITEM = 'CURRENT_COLLECTION_SET_CURRENT_ITEM';
export const CURRENT_COLLECTION_NEXT_ITEM = 'CURRENT_COLLECTION_NEXT_ITEM';
export const CURRENT_COLLECTION_PREVIOUS_ITEM = 'CURRENT_COLLECTION_PREVIOUS_ITEM';
export const CURRENT_COLLECTION_DESELECT = 'CURRENT_COLLECTION_DESELECT';

export const curentCollectionSetItems = ids => ({
    type: CURRENT_COLLECTION_SET_ITEMS,
    ids,
});
export const curentCollectionSetCurrentItem = id => ({
    type: CURRENT_COLLECTION_SET_CURRENT_ITEM,
    id,
});
export const curentCollectionNextItem = () => ({
    type: CURRENT_COLLECTION_NEXT_ITEM,
});
export const curentCollectionPreviousItem = () => ({
    type: CURRENT_COLLECTION_PREVIOUS_ITEM,
});
export const curentCollectionDeselect = () => ({
    type: CURRENT_COLLECTION_DESELECT,
});
