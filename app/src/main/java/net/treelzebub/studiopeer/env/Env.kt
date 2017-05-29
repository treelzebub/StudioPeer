package net.treelzebub.studiopeer.env

/**
 * Created by Tre Murillo on 5/28/17
 */
object StudioPeerEnv {

    lateinit var instance: Env
        private set

    private var isInit = false

    fun init(env: Env) {
        if (isInit) return
        isInit = true
        instance = env
    }

    interface Env {
        val isBeta: Boolean
        val isDebug: Boolean
        val isFinal: Boolean
        val isGenymotion: Boolean
        val testFramework: TestFramework?
        val isTest: Boolean
            get() = testFramework != null

        enum class TestFramework {
            Robolectric,
            Espresso
        }
    }
}
