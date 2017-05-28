package net.treelzebub.studiopeer.env

import android.os.Build
import net.treelzebub.studiopeer.env.StudioPeerEnv.Env

/**
 * Created by Tre Murillo on 5/28/17
 */
open class DevelopEnv(buildConfig: Class<*>) : Env {

    companion object {
        inline fun <reified T: Any> get(): DevelopEnv {
            return DevelopEnv(T::class.java)
        }
    }

    private val flavor: String = buildConfig.getField("FLAVOR").get(null) as String

    override val isFinal get() = !isDebug && !isBeta
    override val isBeta  = flavor.contains("beta")
    override val isDebug = buildConfig.getField("DEBUG").get(null) as Boolean

    override val isGenymotion = Build.MANUFACTURER.contains("Genymotion")
    override val testFramework: Env.TestFramework?
        get() {
            return when {
                "robolectric" == Build.FINGERPRINT -> Env.TestFramework.Robolectric
                detectEspresso() -> Env.TestFramework.Espresso
                else -> null
            }
        }

    private fun detectEspresso(): Boolean {
        return try {
            Class.forName("android.support.test.espresso.Espresso")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }
}