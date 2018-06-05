import {
    CURRENT_COLLECTION_SET_ITEMS,
    CURRENT_COLLECTION_NEXT_ITEM,
    CURRENT_COLLECTION_PREVIOUS_ITEM,
    CURRENT_COLLECTION_DESELECT,
    CURRENT_COLLECTION_SET_CURRENT_ITEM,
} from './actions';

const defaultState = {
    currentIndex: null,
    items: [],
};

const currentCollection = (state = defaultState, action) => {
    switch (action.type) {
    case CURRENT_COLLECTION_SET_ITEMS:
        return {
            ...state,
            items: action.ids,
        };
    case CURRENT_COLLECTION_SET_CURRENT_ITEM:
        return {
            ...state,
            currentIndex: state.items.reduce((found, item, index) => found !== null ? found : action.id == item.id ? index : null, null),
        };
    case CURRENT_COLLECTION_NEXT_ITEM:
        return {
            ...state,
            currentIndex: state.currentIndex < state.items.length - 1 ? state.currentIndex + 1 : 0,
        };
    case CURRENT_COLLECTION_PREVIOUS_ITEM:
        return {
            ...state,
            currentIndex: state.currentIndex > 0 ? state.currentIndex - 1 : state.items.length - 1,
        };
    case CURRENT_COLLECTION_DESELECT:
        return defaultState;
    default:
        return state;
    }
};

export default currentCollection;
