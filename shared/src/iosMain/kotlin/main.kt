import androidx.compose.ui.window.ComposeUIViewController
import com.kmm.project.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    return ComposeUIViewController { App() }
}
