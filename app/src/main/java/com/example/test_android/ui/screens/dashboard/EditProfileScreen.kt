
package com.example.test_android.ui.screens.dashboard

import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.test_android.ui.theme.PoppinsFont
import com.example.test_android.ui.theme.PrimaryColor
import com.example.test_android.ui.theme.GrayColor
import com.example.test_android.ui.theme.SecondaryColor
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoModalBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onImageSelected: (Uri?) -> Unit,
) {

    val context = LocalContext.current
//    var selectedImage by remember { mutableStateOf<Uri?>(null) }

    // Step 1: Temporary file + Uri
    val photoFile = remember {
        File.createTempFile(
            "camera_image", ".jpg",
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )
    }
    val photoUri: Uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider", // 👈 configure in manifest
        photoFile
    )

    // Step 2: Camera launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onImageSelected(photoUri)
                onDismiss()
            }
        }
    )



    // Launcher to pick an image
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri) // Pass back selected image
        onDismiss()          // Close bottom sheet after selection
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,

    ) {
        // 👇 Your modal content here
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Change Avatar",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,


            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp)
                    .shadow(
                        elevation = 8.dp, // 👈 controls shadow size
                        shape = RoundedCornerShape(16.dp), // 👈 border radius
                        clip = false,
                        ambientColor = Color.Gray.copy(alpha = 0.4f),
                        spotColor = Color.Black.copy(alpha = 0.3f)
                    )
                    .clickable {  cameraLauncher.launch(photoUri)}
                    .background(Color.Transparent, RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(

                    modifier = Modifier
                        .size(60.dp)
                        .background(SecondaryColor, CircleShape), // circle background
                    contentAlignment = Alignment.Center

                ) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "camera",
                        tint = PrimaryColor,
                        modifier = Modifier.size(34.dp)
                    )
                }
                Text("Take Photo",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }


            Spacer(modifier = Modifier.height(12.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
                    .height(120.dp)
                    .shadow(
                        elevation = 8.dp, // 👈 controls shadow size
                        shape = RoundedCornerShape(16.dp), // 👈 border radius
                        clip = false,
                        ambientColor = Color.Gray.copy(alpha = 0.4f),
                        spotColor = Color.Black.copy(alpha = 0.3f)
                    )
                    .clickable { galleryLauncher.launch("image/*") }
                    .background(Color.Transparent, RoundedCornerShape(16.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(

                    modifier = Modifier
                        .size(60.dp)
                        .background(SecondaryColor, CircleShape), // circle background
                        contentAlignment = Alignment.Center

                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = "gallery",
                        tint = PrimaryColor,
                        modifier = Modifier.size(34.dp)
                    )
                }
                Text("From Gallery",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController) {


    val scrollState = rememberScrollState()

    var url =
        "https://imgs.search.brave.com/cxK8yYoMfgHkWGAMV5994P1TJD_KjjJr1flcmxioz00/rs:fit:500:0:1:0/g:ce/aHR0cHM6Ly9pMC53/cC5jb20vcGljanVt/Ym8uY29tL3dwLWNv/bnRlbnQvdXBsb2Fk/cy93b21hbi13aXRo/LXN1bi1nbGFzc2Vz/LWluLWZsb3dlci1m/aWVsZC1zdW1tZXIt/ZnJlZS1waG90by5q/cGc_dz02MDAmcXVh/bGl0eT04MA"

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }


    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    var name by remember { mutableStateOf("Ugochukwu") }
    var about by remember { mutableStateOf("Available") }
    var phone by remember { mutableStateOf("+2349059209717") }

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ){
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PrimaryColor

                        )
                    }

                    Text(
                        text = "Profile",
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = PoppinsFont,
                        fontSize = 18.sp,
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ){
                    IconButton(
                        modifier = Modifier
                            .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondaryColor),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "share",
                            tint= PrimaryColor

                        )
                    }

                    IconButton(
                        modifier = Modifier
                            .border(0.dp, Color.Transparent, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(SecondaryColor),
                        onClick = { },

                        ) {

                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "more",
                            tint= PrimaryColor

                        )
                    }
                }


            }
        },

        ) { innerPadding ->
        // parent scrollable column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(16.dp))

            Box( ){

                if (selectedImageUri != null ){
                    AsyncImage(
                        model = selectedImageUri, // Reference your image here
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(24.dp)),
                        contentDescription = "user image",
                        contentScale = ContentScale.Crop
                    )
                    
                } else {
                AsyncImage(
                    model = url, // Reference your image here
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(24.dp)),
                    contentDescription = "user image",
                    contentScale = ContentScale.Crop
                )
                }
                // Floating edit icon
                Box(

                    modifier = Modifier
                        .align(Alignment.BottomEnd) // 👈 position inside image
                        .offset(x = 8.dp, y = 8.dp)
                        .size(24.dp)
                        .background(PrimaryColor, CircleShape) // circle background
                        .clickable { showBottomSheet = true },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.size(16.dp))

            Column(modifier = Modifier.padding(vertical=16.dp)) {
                Text(text = "Name", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = name,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        name = newText
                    },

                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,

                        ),
                    )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "About:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = about,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        about = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),


                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(text = "Phone:", Modifier.padding(horizontal = 20.dp), fontWeight = FontWeight.Bold)
                // 2. Pass the state variable to the value parameter
                TextField(
                    value = phone,
                    onValueChange = { newText: String ->
                        // 3. Update the state variable with the new text
                        phone = newText
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    singleLine = true,
                    shape = RoundedCornerShape(50.dp),

                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = GrayColor,
//                        cursorColor = Color.Blue,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),

                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Lock icon"
                        )
                    },

                    )

            }


        }

    }


    // Show modal when needed
    if (showBottomSheet) {
        PhotoModalBottomSheet(
            sheetState = sheetState,
            onDismiss = { showBottomSheet = false },
            onImageSelected = { uri ->
                selectedImageUri = uri // update main screen state
            },
        )
    }

}











