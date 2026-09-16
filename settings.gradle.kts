pluginManagement {
	repositories {
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}

		mavenCentral()
		gradlePluginPortal()

		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
	}

	plugins { id("net.fabricmc.fabric-loom") version providers.gradleProperty("loom_version") }
}

plugins {
	id("dev.kikugie.stonecutter") version "0.9.7"
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

stonecutter {
	create(rootProject) {
		versions("26.3", "26.2", "26.1.2")
		vcsVersion = "26.3"
	}
}

// Should match your modid
rootProject.name = "woasts"
