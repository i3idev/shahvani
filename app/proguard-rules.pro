# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep Kotlinx Serialization
-keepattributes *Annotation*, InnerClasses, EnclosingMethod
-dontnote kotlinx.serialization.AnnotationsKt

-keep,includedescriptorclasses class *$$serializer { *; }

-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

-keepclasseswithmembers class * {
    kotlinx.serialization.KSerializer serializer(...);
    kotlinx.serialization.KSerializer getSerializer(...);
}

-keepclassmembers class * {
    kotlinx.serialization.KSerializer serializer;
}

# OkHttp
-dontwarn okhttp3.**
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Okio
-dontwarn okio.**
-keepnames class okio.**

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }

# Coil
-keep public class coil3.ImageLoader { *; }
