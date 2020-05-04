<template>
  <div class="app-container">
    <div style="margin:0 0 5px 20px">
      Security Basic Info
    </div>
    <fixed-thead />
  </div>
</template>

<script>
  import FixedThead from './components/FixedThead'
  // import UnfixedThead from './components/UnfixedThead'

  export default {
    name: 'SecurityBasicInfo',
    components: { FixedThead }
  }
</script>
