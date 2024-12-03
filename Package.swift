// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "CapacitorAppodeal",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "CapacitorAppodeal",
            targets: ["AppodealPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "AppodealPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/AppodealPluginPlugin"),
        .testTarget(
            name: "AppodealPluginPluginTests",
            dependencies: ["AppodealPluginPlugin"],
            path: "ios/Tests/AppodealPluginPluginTests")
    ]
)