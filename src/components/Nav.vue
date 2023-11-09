<template>
  <nav class="navbar navbar-expand fixed-top">
    <div class="container">
      <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="#">Features</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Pricing</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Dropdown link
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
          </li>
          <li class="nav-item profileIcon">
            <i class="nav-link">
              <IconProfile />
            </i>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<style>

.navbar-multiple-items {
  display: flex;
}
.navbar-item {
  margin-right: 20px;
}
.navbar-item:last-child {
  margin-right: 0;
}

nav a:hover {
  background-color: white;
  color:black;
}

.navbar {
  --color-background: var(--vt-c-black);
  box-shadow: 0 14px 80px rgba(34, 35, 58, 0.2);
}
.nav-link {
  color:white;
}
.profileIcon:hover {
  background-color: white;
}

</style>
<script setup>
import IconProfile from "@/components/icons/IconProfile.vue";
</script>