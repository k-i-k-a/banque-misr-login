package com.kika.banquemisrlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection.Companion.Ltr
import androidx.compose.ui.text.style.TextDirection.Companion.Rtl
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kika.banquemisrlogin.ui.theme.BanqueMisrLoginTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BanqueMisrLoginTheme {
                LoginDesign()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginDesign(modifier: Modifier = Modifier) {
    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    val isButtonEnabled = usernameText.isNotEmpty() && passwordText.isNotEmpty()
    var locale by remember { mutableStateOf(Locale.getDefault()) }
    val context = LocalContext.current
    var passwordShown by remember { mutableStateOf(false) }
    val updatedContext = context.createConfigurationContext(
        context.resources.configuration.apply {
            setLocale(locale)
        }
    )
    val layoutDirection = if (locale.language == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr
    CompositionLocalProvider(
        LocalContext provides updatedContext,
        LocalLayoutDirection provides layoutDirection
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .padding(PaddingValues(20.dp, 100.dp, 20.dp, 0.dp)),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bm_icon),
                    contentDescription = "Banque Misr Logo"
                )
                Text(
                    text = stringResource(R.string.language),
                    color = colorResource(id = R.color.red),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier.clickable {
                        locale = if (locale == Locale.ENGLISH) Locale("ar") else Locale.ENGLISH
                    })
            }
            OutlinedTextField(
                value = usernameText,
                onValueChange = { usernameText = it },
                label = {
                    Text(
                        stringResource(R.string.username)
                    )
                },
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                )
            )
            OutlinedTextField(
                value = passwordText,
                onValueChange = { passwordText = it },
                label = {
                    Text(
                        stringResource(R.string.password)
                    )
                },
                visualTransformation = if (passwordShown) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    if (passwordShown) {
                        IconButton(onClick = { passwordShown = !passwordShown }) {
                            Icon(
                                painter = painterResource(id = R.drawable.visibility),
                                contentDescription = "show password",
                                Modifier.size(22.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { passwordShown = !passwordShown }) {
                            Icon(
                                painter = painterResource(id = R.drawable.visible),
                                contentDescription = "hide password",
                                Modifier.size(22.dp)
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Gray,
                    unfocusedLabelColor = Color.Gray
                )
            )
            Text(
                text = stringResource(R.string.forgot),
                fontSize = 12.sp,
                textDecoration = TextDecoration.Underline,
                modifier = modifier
                    .align(Alignment.Start)
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                color = Color.Gray
            )
            Button(
                onClick = {  }, modifier.fillMaxWidth(), colors = ButtonColors(
                    colorResource(id = R.color.red), Color.White,
                    colorResource(id = R.color.disabledRed), Color.White
                ), enabled = isButtonEnabled, shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(R.string.login), modifier.padding(8.dp))
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.DarkGray)) {
                        append(stringResource(R.string.help))
                    }
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(id = R.color.red),
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(stringResource(R.string.contact))
                    }
                },
                fontSize = 12.sp,
                modifier = modifier
                    .align(Alignment.Start)
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),

                )
            HorizontalDivider()
            Row(modifier.fillMaxWidth(0.9f), horizontalArrangement = Arrangement.SpaceEvenly) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.our_products),
                        contentDescription = "Products",
                        modifier.size(60.dp)
                    )
                    Text(
                        text = stringResource(R.string.products),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.exchange_rate),
                        contentDescription = "Exchange rate",
                        modifier.size(60.dp)
                    )
                    Text(
                        text = stringResource(R.string.exchange),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.security_tips),
                        contentDescription = "Security tips",
                        modifier.size(60.dp)
                    )
                    Text(
                        text = stringResource(R.string.security),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.nearest_branch_or_atm),
                        contentDescription = "Nearest branch or ATM",
                        modifier.size(60.dp)
                    )
                    Text(
                        text = stringResource(R.string.nearest),
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center,
                        color = Color.DarkGray
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginDesignPreview() {
    BanqueMisrLoginTheme {
        LoginDesign()
    }
}