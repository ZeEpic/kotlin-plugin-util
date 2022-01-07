import com.wife.plugin.commands.CustomCommand
import com.wife.plugin.database.LoginInfo
import com.wife.plugin.database.MongoDB
import com.wife.plugin.util.Listening
import com.wife.plugin.util.Command
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.reflections.Reflections


class Main : JavaPlugin() {

    companion object {

        // To register Main.instance
        val instance get() = getPlugin(Main::class.java)

        // Useful for scheduling code at a later date
        fun doLater(seconds: Int, task: () -> Unit) {
            Bukkit.getScheduler().runTaskLater(instance, Runnable { task() }, seconds * 20L)
        }

    }

    private inline fun <reified A : Annotation, reified T> instancesWithAnnotation(instancePackage: String, run: (A, T) -> Unit) {
        // "me.zeepic.example."
        val rootPackage = this::class.qualifiedName!!.replace(this::class.simpleName!!, "")
        
        // Create reflections instance
        val reflections = Reflections(rootPackage + instancePackage)
        
        // Find all classes in the package that use the annotation A
        val classes = reflections.getTypesAnnotatedWith(A::class.java)
        
        // run the lambda with the annotation it found and a new instance of the class T
        classes.forEach {
            run(it.getAnnotation(A::class.java), it.getDeclaredConstructor().newInstance() as T)
        }
    }

    override fun onEnable() {

        instancesWithAnnotation<Command, CustomCommand>("commands") {
            annotation, command -> getCommand(annotation.name)?.setExecutor(command)
        }

        instancesWithAnnotation<Listening, Listener>("listener") {
            _, listener ->
                server.pluginManager.registerEvents(listener, this)
        }

    }

    override fun onDisable() {
        // Plugin shutdown logic

    }

}
