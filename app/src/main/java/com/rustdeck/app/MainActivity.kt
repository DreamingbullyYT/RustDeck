package com.rustdeck.app

import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF090D16)
private val Panel = Color(0xFF151D2B)
private val PanelRaised = Color(0xFF1C2939)
private val Mint = Color(0xFF69E0B2)
private val Muted = Color(0xFF9AA8BA)
private val White = Color(0xFFF3F6FA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = darkColorScheme(
                background = Ink,
                surface = Panel,
                primary = Mint,
                onPrimary = Ink,
                onBackground = White,
                onSurface = White
            )) {
                RustDeckApp()
            }
        }
    }
}

@Composable
private fun RustDeckApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Home", "Installations", "Settings")

    Box(Modifier.fillMaxSize().background(Ink)) {
        AnimatedBackdrop()
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 22.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Header()
            Box(modifier = Modifier.weight(1f).fillMaxWidth().verticalScroll(rememberScrollState())) {
                Column {
                    when (selectedTab) {
                        0 -> HomeScreen(onInstall = { selectedTab = 1 })
                        1 -> InstallationsScreen()
                        else -> SettingsScreen()
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xE6151D2B))
                    .border(1.dp, Color(0x263C5268), RoundedCornerShape(20.dp))
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    val active = selectedTab == index
                    Box(
                        modifier = Modifier.weight(1f).clip(RoundedCornerShape(15.dp))
                            .background(if (active) Color(0xFF2B3B4D) else Color.Transparent)
                            .clickable { selectedTab = index }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(title, color = if (active) Mint else Muted, fontSize = 13.sp, fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium)
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedBackdrop() {
    val transition = rememberInfiniteTransition(label = "backdrop")
    val shift by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(9000, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "shift"
    )
    Box(Modifier.fillMaxSize().background(
        Brush.linearGradient(
            colors = listOf(
                Color(0xFF101C2B),
                Color(0xFF101725 + (shift * 0x000508).toInt()),
                Ink
            )
        )
    ))
}

@Composable
private fun Header() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier.size(42.dp).clip(RoundedCornerShape(13.dp))
                .background(Brush.linearGradient(listOf(Mint, Color(0xFF3D9B91)))),
            contentAlignment = Alignment.Center
        ) {
            Text("R", color = Ink, fontSize = 25.sp, fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.width(11.dp))
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text("RUSTDECK", color = White, fontSize = 17.sp, fontWeight = FontWeight.ExtraBold, letterSpacing = 2.sp)
            Text("HORIZON GAME LAUNCHER", color = Muted, fontSize = 9.sp, letterSpacing = 1.3.sp)
        }
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.clip(CircleShape).background(Color(0xFF182536))
                .border(1.dp, Color(0x334D6B7B), CircleShape).padding(horizontal = 11.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.size(7.dp).clip(CircleShape).background(Color(0xFFFFC66D)))
            Spacer(Modifier.width(7.dp))
            Text("GUEST", color = Color(0xFFFFD18B), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
    }
}

@Composable
private fun HomeScreen(onInstall: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text("Your worlds are waiting.", color = White, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 33.sp)
            Text("A new home for Horizon on Android.", color = Muted, fontSize = 14.sp)
        }
        Box(
            Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp))
                .background(Brush.linearGradient(listOf(Color(0xFF243B4C), Color(0xFF172536), Color(0xFF18202D))))
                .border(1.dp, Color(0x334D7880), RoundedCornerShape(26.dp))
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(13.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("HORIZON", color = Mint, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                    Spacer(Modifier.weight(1f))
                    Text("ENGINE", color = Muted, fontSize = 10.sp, letterSpacing = 1.5.sp)
                }
                Text("Ready for a new\\nadventure?", color = White, fontSize = 25.sp, fontWeight = FontWeight.Bold, lineHeight = 29.sp)
                Text("Your game installation will appear here once the launcher core is connected.", color = Muted, fontSize = 13.sp, lineHeight = 19.sp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(8.dp).clip(CircleShape).background(Color(0xFFFFC66D)))
                    Spacer(Modifier.width(8.dp))
                    Text("ENGINE NOT INSTALLED", color = Color(0xFFFFD18B), fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                }
                Button(
                    onClick = onInstall,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Mint, contentColor = Ink)
                ) {
                    Text("Set up installation", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoCard("CHANNEL", "Nightly", "Latest engine builds", Modifier.weight(1f))
            InfoCard("ACCOUNT", "Not connected", "Authentication setup", Modifier.weight(1f))
        }
        Text("RustDeck is in early development. Your game files are not changed until you choose to install them.", color = Muted, fontSize = 11.sp, lineHeight = 16.sp)
    }
}

@Composable
private fun InfoCard(title: String, value: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(modifier.clip(RoundedCornerShape(18.dp)).background(Panel)
        .border(1.dp, Color(0x263C5268), RoundedCornerShape(18.dp)).padding(14.dp)) {
        Text(title, color = Muted, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.2.sp)
        Spacer(Modifier.height(8.dp))
        Text(value, color = White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
        Spacer(Modifier.height(3.dp))
        Text(subtitle, color = Muted, fontSize = 10.sp)
    }
}

@Composable
private fun EngineUpdateCard() {
    val ctx = androidx.compose.ui.platform.LocalContext.current
    var text by remember { mutableStateOf("Checking for engine updates…") }
    LaunchedEffect(Unit) {
        text = withContext(Dispatchers.IO) {
            when (val r = PayloadUpdater.check(ctx) { p -> text = p }) {
                is UpdateResult.UpToDate -> "Engine up to date · upstream ${r.installed.upstreamDate.take(10)} @ ${r.installed.upstreamSha.take(9)}"
                is UpdateResult.Updated -> "Engine updated · upstream ${r.installed.upstreamDate.take(10)} @ ${r.installed.upstreamSha.take(9)}"
                is UpdateResult.Failed -> "Engine update failed: ${r.message}"
            }
        }
    }
    Surface(color = PanelRaised, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
        Text(text, color = White, fontSize = 13.sp, modifier = Modifier.padding(18.dp))
    }
}

@Composable
private fun InstallationsScreen() {
    var state by remember { mutableStateOf<Result<List<UpstreamBuild>>?>(null) }
    var reload by remember { mutableIntStateOf(0) }
    LaunchedEffect(reload) {
        state = null
        state = withContext(Dispatchers.IO) { UpstreamReleases.fetch() }
    }
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Installations", color = White, fontSize = 27.sp, fontWeight = FontWeight.Bold)
        Text("Horizon builds published upstream.", color = Muted, fontSize = 14.sp)
        EngineUpdateCard()
        val s = state
        when {
            s == null -> Text("Loading…", color = Muted, fontSize = 14.sp)
            s.isFailure -> {
                Text("Could not load: ${s.exceptionOrNull()?.message}", color = Color(0xFFFFD18B), fontSize = 13.sp)
                Button(onClick = { reload++ }, colors = ButtonDefaults.buttonColors(containerColor = Mint, contentColor = Ink)) { Text("Retry") }
            }
            else -> s.getOrThrow().forEach { b ->
                Surface(color = Panel, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(b.tag, color = White, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)
                        Text(b.date, color = Muted, fontSize = 13.sp)
                        Text(
                            if (b.hasAndroidAsset) "ANDROID BUILD AVAILABLE" else "NO ANDROID BUILD UPSTREAM · NOT INSTALLABLE YET",
                            color = if (b.hasAndroidAsset) Mint else Color(0xFFFFD18B),
                            fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Settings", color = White, fontSize = 27.sp, fontWeight = FontWeight.Bold)
        Text("RustDeck preferences and diagnostics.", color = Muted, fontSize = 14.sp)
        Surface(color = Panel, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Build channel", color = White, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Text("Nightly · Always track the newest available build.", color = Muted, fontSize = 13.sp)
            }
        }
        Surface(color = Panel, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("About RustDeck", color = White, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                Text("Android launcher for Horizon. Engine shell verified; launcher core integration is in progress.", color = Muted, fontSize = 13.sp, lineHeight = 19.sp)
            }
        }
    }
}
