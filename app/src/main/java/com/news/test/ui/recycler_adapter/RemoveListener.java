/*
 *
 *  *  Copyright © 2019,Company name.
 *  *  Written under additional information.
 *
 *
 */

package com.news.test.ui.recycler_adapter;

/**
 * Listener invoked for every element that is going to be removed.
 *
 * @author Aleksandar Gotev
 */

public interface RemoveListener {
    boolean hasToBeRemoved(AdapterItem item);
}
