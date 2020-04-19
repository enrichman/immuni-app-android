package org.immuni.android.ui.forceupdate

import android.content.Context
import androidx.lifecycle.ViewModel
import com.bendingspoons.base.playstore.PlayStoreActions
import com.bendingspoons.base.utils.ExternalLinksHelper
import com.bendingspoons.concierge.ConciergeManager
import com.bendingspoons.oracle.Oracle
import org.immuni.android.api.oracle.model.ImmuniMe
import org.immuni.android.api.oracle.model.ImmuniSettings
import org.koin.core.KoinComponent
import org.koin.core.inject

class ForceUpdateViewModel : ViewModel(), KoinComponent {

    val oracle: Oracle<ImmuniSettings, ImmuniMe> by inject()
    val concierge: ConciergeManager by inject()

    fun goToPlayStoreAppDetails(context: Context) {

        // if we have a custon url
        oracle.settings()?.appUpdateUrl?.let {
            ExternalLinksHelper.openLink(
                context,
                "${it}?id=${concierge.backupPersistentId.id}")
            return
        }

        // otherwise go to play store
        PlayStoreActions.goToPlayStoreAppDetails(context)
    }
}
