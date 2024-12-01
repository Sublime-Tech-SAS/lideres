import SwiftUI
import FirebaseCore
import ComposeApp

@main
struct iOSApp: App {
    
    init(){
        FirebaseApp.configure()
        HelperKt.doInitKoin()
    }
   
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
